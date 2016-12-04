<?php
namespace Wandu\Tmon\Coupon;

use InvalidArgumentException;

abstract class Coupon
{
    /** @var string */
    protected $id;

    /** @var string */
    protected $name;

    /** @var Discount */
    protected $discount;

    /** @var \Wandu\Tmon\RuleSet\RuleSetInterface[] */
    protected $ruleSets;

    /**
     * @param string $id
     * @param string $name
     * @param Discount $discount
     * @param \Wandu\Tmon\RuleSet\RuleSetInterface[] $ruleSets
     */
    public function __construct($id, $name, Discount $discount, array $ruleSets = [])
    {
        if (!is_string($id)) {
            throw new InvalidArgumentException('코드는 반드시 문자열여야 합니다');
        }
        if (!is_string($name)) {
            throw new InvalidArgumentException('쿠폰명은 반드시 문자열여야 합니다');
        }

        $this->id = $id;
        $this->discount = $discount;
        $this->name = $name;
        $this->ruleSets = $ruleSets;
    }

    public function getDiscountPrice($price)
    {
        return $this->discount->getDiscountPrice($price);
    }

    /**
     * @return string
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return int
     */
    public function getDiscount()
    {
        return $this->discount;
    }

    /**
     * @return \Wandu\Tmon\RuleSet\RuleSetInterface[]
     */
    public function getRuleSets()
    {
        return $this->ruleSets;
    }
}
