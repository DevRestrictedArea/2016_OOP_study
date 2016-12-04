<?php
namespace Wandu\Tmon\Coupon;

use Mockery;
use PHPUnit_Framework_TestCase;

class CouponDiscountByMinusTest extends PHPUnit_Framework_TestCase
{
    /** @var \Wandu\Tmon\Coupon\GoodCoupon */
    protected $coupon;

    public function setUp()
    {
        $this->coupon = new GoodCoupon('C1', '알뜰 PC 할인 쿠폰', new DiscountByMinus(50000));
    }

    public function tearDown()
    {
        Mockery::close();
    }

    public function testDiscount()
    {
        $this->assertEquals(30000, $this->coupon->getDiscountPrice(80000));
        $this->assertEquals(10000, $this->coupon->getDiscountPrice(60000));
    }
}
