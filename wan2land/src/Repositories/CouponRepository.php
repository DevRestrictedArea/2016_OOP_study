<?php
namespace Wandu\Tmon\Repositories;

use Wandu\Tmon\Coupon\CartCoupon;
use Wandu\Tmon\Coupon\DiscountByMinus;
use Wandu\Tmon\Coupon\DiscountByPercent;
use Wandu\Tmon\Coupon\GoodCoupon;
use Wandu\Tmon\RuleSet\GoodsCountRule;

class CouponRepository implements RepositoryInterface
{
    /** @var array */
    protected $items = [];

    public function __construct()
    {
        $this->items['C1'] = new GoodCoupon('C1', '알뜰 PC 할인 쿠폰', new DiscountByMinus(50000), [
            new GoodsCountRule(1, 1),
        ]);
        $this->items['C1-1'] = new GoodCoupon('C1-1', '알뜰 PC 할인 쿠폰 2', new DiscountByMinus(40000), [
            new GoodsCountRule(5, 10),
        ]);
        $this->items['C2'] = new CartCoupon('C2', '장바구니 쿠폰', new DiscountByPercent(0.1));
        $this->items['C3'] = new CartCoupon('C3', '선착순 할인 할인 쿠폰(10명)', new DiscountByPercent(0.2));
    }

    /**
     * @param $id
     * @return \Wandu\Tmon\Coupon\CartCoupon
     */
    public function getCoupon($id)
    {
        return isset($this->items[$id]) ? $this->items[$id] : null;
    }
}
