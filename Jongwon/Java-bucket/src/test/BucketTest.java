package test;
/**
 * Created by lasto on 2016-12-10.
 */

import junit.framework.*;
import main.coupon.Coupon;
import main.Wishes;

import java.util.List;

public class BucketTest extends TestCase {
    public void calcCost(List<Coupon> bucketCoupons, List<Wishes> wishesObjects) {

        for (Wishes wishes : wishesObjects) {
            float total = 0;
            total += wishes.getGoods().getPrice() * wishes.getCount();
            for (Coupon coupon : bucketCoupons) {

            }
        }
    }
}