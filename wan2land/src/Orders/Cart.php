<?php
namespace Wandu\Tmon\Orders;

class Cart
{
    /** @var string */
    protected $id;

    /** @var string */
    protected $username;

    /** @var \Wandu\Tmon\Coupon\Coupon[] */
    protected $cartCoupons;

    /** @var \Wandu\Tmon\Orders\Order[] */
    protected $orders;

    public function __construct($id, $username, array $cartCoupons = [], array $orders = [])
    {
        $this->id = $id;
        $this->username = $username;
        $this->cartCoupons = $cartCoupons;
        $this->orders = $orders;
    }

    public function addCart(Cart $cart)
    {
        if ($cart->getId() !== $this->getId()) {
            throw new \RuntimeException("카트 아이디가 다릅니다");
        }
        if ($this->getUsername() !== $cart->getUsername()) {
            throw new \RuntimeException("카트의 회원 아이디가 다릅니다");
        }
        foreach ($cart->getCartCoupons() as $cartCoupon) {
            if (!in_array($cartCoupon, $this->cartCoupons)) {
                $this->cartCoupons[] = $cartCoupon;
            }
        }
        // id 기반으로 바꿔야하는데..
        foreach ($cart->getOrders() as $order) {
            if (!in_array($order, $this->orders)) {
                $this->orders[] = $order;
            }
        }
    }

    public function getTotalPrice()
    {
        $total = 0;
        foreach ($this->orders as $order) {
            $total += $order->getPrice();
        }
        return $total;
    }

    /**
     * @param \Wandu\Tmon\Orders\Order $order
     */
    public function addOrder(Order $order)
    {
        $this->orders[] = $order;
    }

    /**
     * @return string
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return mixed
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * @return \Wandu\Tmon\Coupon\Coupon[]
     */
    public function getCartCoupons()
    {
        return $this->cartCoupons;
    }

    /**
     * @return \Wandu\Tmon\Orders\Order[]
     */
    public function getOrders()
    {
        return $this->orders;
    }
}
