<?php
namespace Wandu\Tmon\Coupon;

class DiscountByMinus extends  Discount
{
    /**
     * {@inheritdoc}
     */
    public function getDiscountPrice($price)
    {
        return $price - $this->discount;
    }
}
