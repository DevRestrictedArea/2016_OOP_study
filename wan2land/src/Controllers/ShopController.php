<?php
namespace Wandu\Tmon\Controllers;

use Psr\Http\Message\ServerRequestInterface;
use Wandu\Http\Psr\Factory\ResponseFactory;
use Wandu\Tmon\Orders\TextParser;
use Wandu\Tmon\Repositories\CartRepository;
use Wandu\Tmon\View\RenderInterface;

class ShopController extends Controller
{
    /** @var \Wandu\Tmon\Orders\TextParser */
    protected $parser;

    public function __construct(
        ResponseFactory $response,
        RenderInterface $view,
        TextParser $parser
    ) {
        parent::__construct($response, $view);
        $this->parser = $parser;
    }


    public function order()
    {
        return $this->response->create($this->view->render('order'));
    }

    public function ordering(ServerRequestInterface $request)
    {
        $params = $request->getParsedBody();
        $session = $request->getAttribute('session');

        if (!$session->has('carts')) {
            $session->set('carts', new CartRepository());
        }

        $cart = $this->parser->parse($params['order']);
        $session->get('carts')->addItem($cart);

        return $this->response->create($this->view->render('ordering', [
            'cart' => $cart,
        ]));
    }

    public function cart()
    {
        return $this->response->create($this->view->render('cart'));
    }

    public function search(ServerRequestInterface $request)
    {
        $params = $request->getParsedBody();
        $session = $request->getAttribute('session');

        if (!$session->has('carts')) {
            $session->set('carts', new CartRepository());
        }

        $cart = $session->get('carts')->getItem($params['cart_id']);

        return $this->response->create($this->view->render('cart', [
            'cart' => $cart,
        ]));
    }
}
