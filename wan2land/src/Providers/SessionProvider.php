<?php
namespace Wandu\Tmon\Providers;

use ArrayAccess;
use Wandu\DI\ContainerInterface;
use Wandu\DI\ServiceProviderInterface;
use Wandu\Http\Contracts\SessionAdapterInterface;
use Wandu\Http\Cookie\CookieJarFactory;
use Wandu\Http\Session\Adapter\FileAdapter;
use Wandu\Http\Session\SessionFactory;

class SessionProvider implements ServiceProviderInterface
{
    public function register(ContainerInterface $app, ArrayAccess $config = null)
    {
        $app->bind(CookieJarFactory::class);
        $app->closure(SessionAdapterInterface::class, function (ContainerInterface $app) {
            return new FileAdapter(
                $app->path('session'),
                $app->config('session.timeout', 1800)
            );
        });
        $app->closure(SessionFactory::class, function (ContainerInterface $app) {
            return new SessionFactory($app[SessionAdapterInterface::class], [
                'timeout' => $app->config('session.timeout', 1800),
                'name' => $app->config('session.name', "TmonSessId"),
            ]);
        });
    }
}
