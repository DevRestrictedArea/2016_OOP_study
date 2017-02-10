package main.coupon;

import main.Wishes;

/**
 * Created by lasto on 2016-12-10.
 */
public class C3 extends Coupon {

    public C3() {
        super("C3", "선착순 할인 쿠폰(10명)", 0.2f);
    }

    @Override
    public int sale(int total, int count) {
        return (int) (total - total * discount);
    }

    @Override
    public boolean condition(Wishes wish) {
        return false;
    }

    @Override
    public boolean condition(int totalCost) {
        return false;
    }
}
