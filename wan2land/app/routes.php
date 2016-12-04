<?php
use Wandu\Tmon\Controllers\HomeController;
use Wandu\Tmon\Controllers\ShopController;
use Wandu\Tmon\Middlewares\Responsify;
use Wandu\Tmon\Middlewares\Sessionify;
use Wandu\Router\Router;

return function (Router $router) {
    $router->group([
        'middleware' => [
            Sessionify::class,
            Responsify::class,
        ]
    ], function (Router $router) {
        $router->get('/', HomeController::class, 'index');

        $router->get('/order', ShopController::class, 'order');
        $router->post('/order', ShopController::class, 'ordering');

        $router->get('/cart', ShopController::class, 'cart');
        $router->post('/cart', ShopController::class, 'search');
    });
};
