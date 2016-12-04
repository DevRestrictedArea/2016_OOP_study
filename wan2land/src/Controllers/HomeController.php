<?php
namespace Wandu\Tmon\Controllers;

use Psr\Http\Message\ServerRequestInterface;

class HomeController extends Controller
{
    public function index(ServerRequestInterface $request)
    {
        return $this->response->create($this->view->render('home'));
    }
}
