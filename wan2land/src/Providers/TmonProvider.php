<?php
namespace Wandu\Tmon\Providers;

use Wandu\DI\ContainerInterface;
use Wandu\DI\ServiceProviderInterface;
use Wandu\Tmon\Orders\TextParser;
use Wandu\Tmon\Repositories\CouponRepository;
use Wandu\Tmon\Repositories\GoodRepository;

class TmonProvider implements ServiceProviderInterface
{
    public function register(ContainerInterface $app)
    {
        $app->bind(GoodRepository::class);
        $app->bind(CouponRepository::class);
        $app->bind(TextParser::class);
    }
}
