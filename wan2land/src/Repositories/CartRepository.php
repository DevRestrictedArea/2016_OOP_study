<?php
namespace Wandu\Tmon\Repositories;

use Wandu\Tmon\Orders\Cart;

class CartRepository implements RepositoryInterface
{
    /** @var array */
    protected $items = [];

    public function addItem(Cart $cart)
    {
        $this->items[$cart->getId()] = $cart;
    }

    /**
     * @param string $id
     * @return \Wandu\Tmon\Orders\Cart
     */
    public function getItem($id)
    {
        return isset($this->items[$id]) ? $this->items[$id] : null;
    }

    public function removeItem($id)
    {
        unset($this->items[$id]);
    }
}
