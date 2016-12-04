<?php
namespace Wandu\Tmon;

use Wandu\DI\Container;
use Wandu\Http\Psr\Factory\ServerRequestFactory;
use Wandu\Http\Psr\Sender\ResponseSender;

class Application extends Container
{
    public function __construct($basePath)
    {
        $this->instance('base_path', $basePath)->freeze('base_path');
        parent::__construct();
    }

    public function bootstrap()
    {
        $this->call(require $this->get('base_path'). '/app/providers.php');
        $this->call(require $this->get('base_path') .'/app/routes.php');
    }

    public function execute()
    {
        $request = $this->get(ServerRequestFactory::class)->fromGlobals();
        $response = $this->get('router')->dispatch($request);
        $this->get(ResponseSender::class)->send($response);
    }
}
