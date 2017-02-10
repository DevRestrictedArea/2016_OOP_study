package main.coupon;

import main.Wishes;

/**
 * Created by lasto on 2016-12-10.
 */
public class C1_1 extends Coupon {
    public C1_1() {
        super("C1-1", "알뜰 PC 할인 쿠폰 2", 40000);
    }

    @Override
    public int sale(int total, int count) {
        return (int) (total - this.discount * count);
    }

    @Override
    public boolean condition(Wishes wish) {
        assert wish.getGoods().getCode().equals("L") : "C1 쿠폰은 알뜰 PC에 사용 가능한 쿠폰입니다.";
        assert wish.getCount() == 1 : "C1 쿠폰은 1개만 적용 가능한 쿠폰입니다.";
        assert wish.getCoupon().size() == 1 : "C1 쿠폰은 중복 사용으로 인해 사용이 불가능합니다.";
        return true;
    }

    @Override
    public boolean condition(int totalCost) {
        return false;
    }
}
