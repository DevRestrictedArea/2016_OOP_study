package main;

import main.coupon.Coupon;
import main.coupon.CouponList;
import main.goods.Goods;
import main.goods.GoodsList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lasto on 2016-12-07.
 */
public class Order {


    public static void main(String[] args) {

        GoodsList goodsList = new GoodsList();
        List<Goods> goods = goodsList.getList();

        CouponList couponList = new CouponList();
        List<Coupon> coupons = couponList.getList();


        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader b = new BufferedReader(r);
        try {
            String userInput =  b.readLine();
            System.out.println("INPUT: "+ userInput);

            Parser parser = new Parser();
            parser.parseInput(userInput);

            String[] info = parser.parseVariable(userInput);
            int bucketID = Integer.parseInt(info[0]);
            String userID = info[1];
            String bucketCouponCode = info[2];
            String wishesCode = info[3];

            List<Coupon> bucketCoupons = new ArrayList<Coupon>();
            String[][] bucketCouponCodes = parser.splitBar(bucketCouponCode);
            for (String item : bucketCouponCodes[0]){
                for (Coupon coupon : coupons ){
                    if (item.equals(coupon.getCode())) {
                        bucketCoupons.add(coupon);
                    }
                }
            }

            List<Wishes> wishesObjects = new ArrayList<Wishes>();
            String[] wishesCodes = wishesCode.split(",");
            for (String item : wishesCodes){
                String[] element = item.split("\\|");

                // good object element
                Goods goodObj = null;
                for (Goods good : goods) {
                    if (element[0].equals(good.getCode())){
                        goodObj = good;
                    }
                }
                // coupon object
                List<Coupon> couponObjects = new ArrayList<Coupon>();
                if (element.length > 2) {
                    for (int i=2;i<element.length;i++) {
                        for (Coupon coupon : coupons) {
                            if (element[i].equals(coupon.getCode())) {
                                couponObjects.add(coupon);
                            }
                        }
                    }
                }

                Wishes wishes = new Wishes(goodObj,Integer.parseInt(element[1]) ,couponObjects);
                wishesObjects.add(wishes);
            }

            // wishesObjects null check !
            Bucket bucket = new Bucket(bucketID,userID, bucketCoupons, wishesObjects);
            bucket.printId();
            bucket.calcCost();
            bucket.conditionCheck();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
