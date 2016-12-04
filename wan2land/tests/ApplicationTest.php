<?php
namespace Wandu\Tmon;

use Mockery;
use PHPUnit_Framework_TestCase;

class ApplicationTest extends PHPUnit_Framework_TestCase
{
    public function setUp()
    {
    }

    public function tearDown()
    {
        Mockery::close();
    }

    public function testHello()
    {
        $this->assertEquals(1, 1);
    }
}
