<?php
namespace Wandu\Tmon\Providers;

use Wandu\DI\ContainerInterface;
use Wandu\DI\ServiceProviderInterface;
use Wandu\Router\ClassLoader\WanduLoader;
use Wandu\Router\Contracts\ClassLoaderInterface;
use Wandu\Router\Router;

class RouterProvider implements  ServiceProviderInterface
{
    public function register(ContainerInterface $app)
    {
        $app->bind(ClassLoaderInterface::class, WanduLoader::class);
        $app->bind(Router::class);
        $app->alias('router', Router::class);
    }
}
