<?php
namespace Wandu\Tmon\Coupon;

abstract class Discount
{
    /**
     * @param int $discount
     */
    public function __construct($discount)
    {
        $this->discount = $discount;
    }

    /**
     * @param int $price
     * @return int
     */
    abstract public function getDiscountPrice($price);
}
