package main.coupon;

import main.Wishes;

/**
 * Created by lasto on 2016-12-10.
 */
public class C2 extends Coupon {

    public C2() {
        super("C2", "장바구니 쿠폰", 0.1f);
    }

    @Override
    public int sale(int total, int count) {
        return (int) (total - total * this.discount);
    }

    @Override
    public boolean condition(Wishes wish) {
        return false;
    }

    @Override
    public boolean condition(int totalCost){
        assert totalCost > 300000 : "C2 쿠폰은 장바구니의 금액이 300,000원 이상일 때 사용 가능합니다.";
        return true;
    }
}
