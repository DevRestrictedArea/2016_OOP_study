<?php
namespace Wandu\Tmon\RuleSet;

class GoodsCountRule implements RuleSetInterface
{
    /** @var int */
    protected $count;

    /** @var int */
    protected $max;

    /** @var int */
    protected $min;

    public function __construct($min = 1, $max = 1)
    {
        $this->min = $min;
        $this->max = $max;
    }

    /**
     * @param int $count
     * @return \Wandu\Tmon\RuleSet\GoodsCountRule
     */
    public function withGoodsCount($count)
    {
        $rule = clone $this;
        $rule->count = $count;
        return $rule;
    }

    /**
     * @return bool
     */
    public function isValid()
    {
        return $this->min <= $this->count && $this->count <= $this->max;
    }
}
