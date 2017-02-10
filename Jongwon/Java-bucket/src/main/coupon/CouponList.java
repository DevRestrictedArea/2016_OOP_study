package main.coupon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lasto on 2016-12-09.
 * 좀 더 나은 방법이 없을까(Grouping)
 */
public class CouponList {
    private Coupon c1 = new C1();
    private Coupon c1_1 = new C1_1();
    private Coupon c2 = new C2();
    private Coupon c3 = new C3();

    public List<Coupon> getList(){
        return getAllList();
    }

    private List<Coupon> getAllList(){
        List<Coupon> couponList = new ArrayList<Coupon>();

        couponList.add(c1);
        couponList.add(c1_1);
        couponList.add(c2);
        couponList.add(c3);

        return couponList;
    }

}
