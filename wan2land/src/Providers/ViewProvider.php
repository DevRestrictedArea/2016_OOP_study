<?php
namespace Wandu\Tmon\Providers;

use Wandu\DI\ContainerInterface;
use Wandu\DI\ServiceProviderInterface;
use Wandu\Tmon\View\RenderInterface;
use Wandu\Tmon\View\SimpleTemplate;

class ViewProvider implements ServiceProviderInterface
{
    public function register(ContainerInterface $app)
    {
        $app->closure(RenderInterface::class, function ($app) {
            return new SimpleTemplate($app->path('views'));
        });
    }
}
