<?php
namespace Wandu\Tmon\Providers;

use ArrayAccess;
use Wandu\DI\ContainerInterface;
use Wandu\DI\ServiceProviderInterface;
use Wandu\Http\Psr\Factory\ResponseFactory;
use Wandu\Http\Psr\Factory\ServerRequestFactory;
use Wandu\Http\Psr\Factory\UploadedFileFactory;
use Wandu\Http\Psr\Sender\ResponseSender;

class HttpProvider implements ServiceProviderInterface
{
    public function register(ContainerInterface $app, ArrayAccess $config = null)
    {
        $app->bind(ServerRequestFactory::class);
        $app->bind(UploadedFileFactory::class);
        $app->bind(ResponseFactory::class);
        $app->bind(ResponseSender::class);
    }
}
