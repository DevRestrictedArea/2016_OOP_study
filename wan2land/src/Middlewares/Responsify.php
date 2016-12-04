<?php
namespace Wandu\Tmon\Middlewares;

use Closure;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Wandu\Http\Psr\Factory\ResponseFactory;

class Responsify
{
    /** @var \Wandu\Http\Psr\Factory\ResponseFactory */
    protected $factory;

    /**
     * @param \Wandu\Http\Psr\Factory\ResponseFactory $factory
     */
    public function __construct(ResponseFactory $factory)
    {
        $this->factory = $factory;
    }

    /**
     * @param \Psr\Http\Message\ServerRequestInterface $request
     * @param \Closure $next
     * @return \Psr\Http\Message\ResponseInterface
     */
    public function handle(ServerRequestInterface $request, Closure $next)
    {
        $response = $next($request);
        if ($response instanceof ResponseInterface) {
            return $response;
        }
        if (is_string($response)) {
            return $this->factory->create($response);
        }
        if (is_array($response)) {
            return $this->factory->json($response);
        }
        return $this->factory->create('Unknown Type Type Of Response.', 500);
    }
}
