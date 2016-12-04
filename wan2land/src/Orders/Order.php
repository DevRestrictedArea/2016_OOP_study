<?php
namespace Wandu\Tmon\Orders;

use Wandu\Tmon\Coupon\Coupon;
use Wandu\Tmon\Models\Good;

class Order
{
    protected $good;

    protected $count;

    protected $coupon;

    public function __construct(Good $good, $count, Coupon $coupon = null)
    {
        $this->good = $good;
        $this->count = $count;
        $this->coupon = $coupon;
    }

    /**
     * @return \Wandu\Tmon\Models\Good
     */
    public function getGood()
    {
        return $this->good;
    }

    /**
     * @return mixed
     */
    public function getCount()
    {
        return $this->count;
    }

    public function getPrice()
    {
        return $this->count * $this->getCoupon()->getDiscountPrice($this->getGood()->getPrice());
    }


    /**
     * @return \Wandu\Tmon\Coupon\Coupon
     */
    public function getCoupon()
    {
        return $this->coupon;
    }
}
