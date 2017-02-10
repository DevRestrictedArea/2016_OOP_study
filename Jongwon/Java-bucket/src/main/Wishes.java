package main;

import main.coupon.Coupon;
import main.goods.Goods;

import java.util.List;

/**
 * Created by lasto on 2016-12-08.
 */
public class Wishes {
    private Goods goods;
    private int count;
    private List<Coupon> coupon;

    public Goods getGoods() {
        return goods;
    }

    public int getCount() {
        return count;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public Wishes(Goods goods, int count, List<Coupon> coupon) {
        this.goods = goods;
        this.count = count;
        this.coupon = coupon;

    }
}
