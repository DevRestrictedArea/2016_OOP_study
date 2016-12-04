<?php
namespace Wandu\Tmon\RuleSet;

use Mockery;
use PHPUnit_Framework_TestCase;

class GoodsCountRuleTest extends PHPUnit_Framework_TestCase
{
    public function tearDown()
    {
        Mockery::close();
    }

    public function testIsValidOnlyOne()
    {
        $rule = new GoodsCountRule(3, 3);

        $this->assertFalse($rule->withGoodsCount(2)->isValid());
        $this->assertTrue($rule->withGoodsCount(3)->isValid());
        $this->assertFalse($rule->withGoodsCount(4)->isValid());
    }

    public function testIsValidRange()
    {
        $rule = new GoodsCountRule(2, 5);

        $this->assertFalse($rule->withGoodsCount(1)->isValid());
        $this->assertTrue($rule->withGoodsCount(2)->isValid());
        $this->assertTrue($rule->withGoodsCount(3)->isValid());
        $this->assertTrue($rule->withGoodsCount(4)->isValid());
        $this->assertTrue($rule->withGoodsCount(5)->isValid());
        $this->assertFalse($rule->withGoodsCount(6)->isValid());
    }
}
