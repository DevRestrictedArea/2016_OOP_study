<?php
namespace Wandu\Tmon\Orders;

use Wandu\Tmon\Repositories\CouponRepository;
use Wandu\Tmon\Repositories\GoodRepository;

class TextParser
{
    /** @var \Wandu\Tmon\Repositories\GoodRepository */
    protected $goods;

    /** @var \Wandu\Tmon\Repositories\CouponRepository */
    protected $coupons;

    public function __construct(GoodRepository $goods, CouponRepository $coupons)
    {
        $this->goods = $goods;
        $this->coupons = $coupons;
    }

    public function parse($text)
    {
        if (preg_match("/^" .
            "(?<cartid>[0-9]{6})," .
            "(?<username>[A-Za-z0-9]{4,12})," .
            "(?<cart_coupons>[A-Z0-9-\\|]*)," .
            "(?<orders>[A-Z0-9-\\|]+(,[A-Z0-9-\\|]+)*)" .
            "/", $text, $matches)) {

            return new Cart(
                $matches['cartid'],
                $matches['username'],
                $this->parseCartCoupons($matches['cart_coupons']),
                $this->parseOrders($matches['orders'])
            );
        } else {
            throw new \RuntimeException("잘못된 패턴입니다!");
        }
    }

    protected function parseCartCoupons($text)
    {
        $couponsToReturn = [];
        foreach (explode('|', $text) as $couponId) {
            if ($couponId !== '') {
                $coupon = $this->coupons->getCoupon($couponId);
                if (!isset($coupon)) {
                    throw new \RuntimeException("'{$couponId}'는 존재하지 않는 카트 쿠폰입니다");
                }
                $couponsToReturn[] = $coupon;
            }
        }
        return $couponsToReturn;
    }

    protected function parseOrders($text)
    {
        $ordersToReturn = [];
        foreach (explode(',', $text) as $order) {
            $ordersToReturn[] = $this->parseOrder($order);
        }
        return $ordersToReturn;
    }

    protected function parseOrder($text)
    {
        $orders = explode('|', $text);
        if (count($orders) !== 2 && count($orders) !== 3 ) {
            throw new \RuntimeException("잘못된 패턴입니다, 주문쪽이 잘못 되었습니다.");
        }

        $good = $this->goods->getGood($orders[0]);
        if (!isset($good)) {
            throw new \RuntimeException("'{$orders[0]}'는 존재하지 않는 상품입니다");
        }
        $coupon = null;
        if (isset($orders[2])) {
            $coupon = $this->coupons->getCoupon($orders[2]);
            if (!isset($coupon)) {
                throw new \RuntimeException("'{$orders[2]}'는 존재하지 않는 쿠폰입니다");
            }
        }
        return new Order($good, $orders[1], $coupon);
    }
}
