<?php
namespace Wandu\Tmon\Coupon;

class DiscountByPercent extends Discount
{
    /**
     * {@inheritdoc}
     */
    public function getDiscountPrice($price)
    {
        // 최소단위는 10원
        return ((int)($price - ($this->discount * $price))/10) * 10;
    }
}
