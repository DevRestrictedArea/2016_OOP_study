package main;

import main.coupon.Coupon;

import java.util.List;

/**
 * Created by lasto on 2016-12-08.
 */
public class Bucket {
    private int BucketID;
    private String UserID;
    private List<Coupon> bucketCoupon;
    private List<Wishes> wishes;

    public Bucket(int bucketID, String userID, List<Coupon> bucketCoupon, List<Wishes> wishes) {
        BucketID = bucketID;
        UserID = userID;
        this.bucketCoupon = bucketCoupon;
        this.wishes = wishes;
    }

    public int getBucketID() {
        return BucketID;
    }

    public String getUserID() {
        return UserID;
    }

    public List<Coupon> getBucketCoupon() {
        return bucketCoupon;
    }

    public List<Wishes> getWishes() {
        return wishes;
    }

    public int totalCost(){
        int total = 0;
        for (Wishes wish : this.wishes){
            total += wish.getGoods().getPrice() * wish.getCount();
        }
        return total;
    }

    public boolean conditionCheck() {
        boolean valid = true;
        for (Wishes wish : this.wishes) {
            for (Coupon coupon : wish.getCoupon()){
                valid &= coupon.condition(wish);
            }
        }
        for (Coupon coupon : this.bucketCoupon) {
            valid &= coupon.condition(this.totalCost());
        }
        return valid;
    }

    public void calcCost() {
        int total = 0;

        // cost: 개별 장바구니 금액, total: 누적 금액
        for (Wishes wish : this.wishes) {
            int cost = 0;
            cost += wish.getGoods().getPrice() * wish.getCount();
            System.out.println(wish.getGoods().getCode() + " " + wish.getCount() + "개 할인 전 금액: " + cost + "원, ");

            String couponCodes = new String();
            for (Coupon coupon : wish.getCoupon()) {
                cost = coupon.sale(cost, wish.getCount());
                couponCodes = couponCodes.concat(coupon.getCode() + ", ");
            }

            System.out.println("할인 후 금액: " + cost + "원, 적용된 쿠폰: " + couponCodes);
            System.out.println("");

            total += cost;
        }

        System.out.print("전체 금액: " + this.totalCost() + "원 / ");
        for (Coupon coupon : this.bucketCoupon){
            total = coupon.sale(total, 1);
        }
        System.out.println("총 할인 금액: " + total + "원");
    }

    public void printId() {
        System.out.println("장바구니 ID는 " + this.BucketID + "입니다.");
        System.out.println("");
    }
}
