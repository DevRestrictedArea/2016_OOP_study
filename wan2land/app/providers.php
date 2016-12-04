<?php
use Wandu\DI\ContainerInterface;
use Wandu\Tmon\Providers\ContainerProvider;
use Wandu\Tmon\Providers\HttpProvider;
use Wandu\Tmon\Providers\RouterProvider;
use Wandu\Tmon\Providers\SessionProvider;
use Wandu\Tmon\Providers\TmonProvider;
use Wandu\Tmon\Providers\ViewProvider;

return function (ContainerInterface $container) {
    $container->register(new ContainerProvider());
    $container->register(new RouterProvider());
    $container->register(new HttpProvider());
    $container->register(new SessionProvider());
    $container->register(new ViewProvider());
    $container->register(new TmonProvider());
};
