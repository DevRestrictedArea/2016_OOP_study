<?php
namespace Wandu\Tmon\Coupon;

use Mockery;
use PHPUnit_Framework_TestCase;

class CouponDiscountByPercentTest extends PHPUnit_Framework_TestCase
{
    /** @var \Wandu\Tmon\Coupon\GoodCoupon */
    protected $coupon;

    public function setUp()
    {
        $this->coupon = new GoodCoupon('C2', '장바구니 쿠폰', new DiscountByPercent(0.1));
    }

    public function tearDown()
    {
        Mockery::close();
    }

    public function testDiscount()
    {
        $this->assertEquals(72000, $this->coupon->getDiscountPrice(80000));
        $this->assertEquals(54000, $this->coupon->getDiscountPrice(60000));
    }
}
