<?php
namespace Wandu\Tmon\Controllers;

use Wandu\Http\Psr\Factory\ResponseFactory;
use Wandu\Router\Contracts\ControllerInterface;
use Wandu\Tmon\View\RenderInterface;

abstract class Controller implements ControllerInterface
{
    public function __construct(ResponseFactory $response, RenderInterface $view)
    {
        $this->response = $response;
        $this->view = $view;
    }
}
