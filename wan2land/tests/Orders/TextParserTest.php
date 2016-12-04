<?php
namespace Wandu\Tmon\Orders;

use Mockery;
use PHPUnit_Framework_TestCase;
use Wandu\Tmon\Coupon\Coupon;
use Wandu\Tmon\Models\Good;
use Wandu\Tmon\Repositories\CouponRepository;
use Wandu\Tmon\Repositories\GoodRepository;

class TextParserTest extends PHPUnit_Framework_TestCase
{
    /** @var \Wandu\Tmon\Repositories\GoodRepository */
    protected $goods;

    /** @var \Wandu\Tmon\Repositories\CouponRepository */
    protected $coupons;

    /** @var \Wandu\Tmon\Orders\TextParser */
    protected $parser;

    public function setUp()
    {
        $this->goods = new GoodRepository();
        $this->coupons = new CouponRepository();
        $this->parser = new TextParser($this->goods, $this->coupons);
    }

    public function tearDown()
    {
        Mockery::close();
    }

    public function testParsing()
    {
        $cart = $this->parser->parse('123456,tmon,C3,H|1|C2');
        $this->assertInstanceOf(Cart::class, $cart);
        $this->assertEquals(123456, $cart->getId());
        $this->assertEquals('tmon', $cart->getUsername());

        $cartCoupons = $cart->getCartCoupons();
        $this->assertEquals(1, count($cartCoupons));
        $this->assertInstanceOf(Coupon::class, $cartCoupons[0]);
        $this->assertEquals('C3', $cartCoupons[0]->getId());

        $orders = $cart->getOrders();
        $this->assertEquals(1, count($orders));
        $this->assertInstanceOf(Order::class, $orders[0]);

        $this->assertInstanceOf(Good::class, $orders[0]->getGood());
        $this->assertEquals('H', $orders[0]->getGood()->getId());

        $this->assertEquals(1, $orders[0]->getCount());
        $this->assertInstanceOf(Coupon::class, $orders[0]->getCoupon());
        $this->assertEquals('C2', $orders[0]->getCoupon()->getId());



        $cart = $this->parser->parse('123457,tmon2,,L|2|C1,H|1');

        $this->assertEquals(new Cart(123457, 'tmon2', [], [
            new Order($this->goods->getGood('L'), 2, $this->coupons->getCoupon('C1')),
            new Order($this->goods->getGood('H'), 1),
        ]), $cart);

        $cart = $this->parser->parse('123457,tmon3,C2|C3,H|1,M|1,L|1');

        $this->assertEquals(new Cart(123457, 'tmon3', [
            $this->coupons->getCoupon('C2'),
            $this->coupons->getCoupon('C3'),
        ], [
            new Order($this->goods->getGood('H'), 1),
            new Order($this->goods->getGood('M'), 1),
            new Order($this->goods->getGood('L'), 1),
        ]), $cart);
    }
}
