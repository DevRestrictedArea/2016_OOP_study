package main.coupon;

import main.Wishes;

import java.util.List;

/**
 * Created by lasto on 2016-12-07.
 */

public abstract class Coupon {
    protected String code;
    protected String description;
    protected float discount;

    public Coupon(String code, String description, float discount) {
        this.code = code;
        this.description = description;
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public abstract int sale(int total, int count);

    public abstract boolean condition(Wishes wish);
    public abstract boolean condition(int totalCost);


}
