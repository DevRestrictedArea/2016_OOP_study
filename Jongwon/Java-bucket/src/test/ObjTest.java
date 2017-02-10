package test;
/**
 * Created by lasto on 2016-12-09.
 */

import junit.framework.*;
import main.coupon.Coupon;
import main.coupon.CouponList;
import main.goods.Goods;
import main.goods.GoodsList;

import java.util.*;

public class ObjTest extends TestCase {
    public void testObj() throws Exception {
        GoodsList goodsList = new GoodsList();
        List<Goods> goods = goodsList.getList();

        CouponList couponList = new CouponList();
        List<Coupon> coupons = couponList.getList();


        System.out.println(coupons.size());
    }
}