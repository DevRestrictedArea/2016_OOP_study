<?php
namespace Wandu\Tmon\Providers;

use Wandu\DI\Helper\ConfigLoader;
use Wandu\DI\ContainerInterface;
use Wandu\DI\Helper\PathLoader;
use Wandu\DI\ServiceProviderInterface;

class ContainerProvider implements ServiceProviderInterface
{
    public function register(ContainerInterface $app)
    {
        $app->closure('path', function (ContainerInterface $app) {
            return new PathLoader($app->get('base_path'));
        });
        $app->closure('config', function (ContainerInterface $app) {
            return new ConfigLoader($app->path('config'));
        });
    }
}
