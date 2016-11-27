/**
 * Order
 */

import java.util.HashMap;

public class Order {

    private String basketID;
    private String userID;
    private Coupon[] basketCoupon;
    private Product[] products;

    public Order (String orderString, HashMap<String , Product> productList, HashMap<String , Coupon> couponList) {
        String[] orderInfo = orderString.split(",");

        if(orderInfo.length < 4){
            System.err.println("Please check input Data: [ "+orderString+" ]");
        } else {
            basketID = orderInfo[0];
            userID = orderInfo[1];
            if(orderInfo[2].equals("")){
                basketCoupon = null;
            } else {
                String[] cpcode = orderInfo[2].split("\\|");
                basketCoupon = new Coupon[cpcode.length];
                for (int i=0; i < cpcode.length ; i++) {
                    basketCoupon[i] = couponList.get(cpcode[i]);
                }
            }
            products = new Product[orderInfo.length-3];
            for(int i=3; i<orderInfo.length; i++) {
                String[] productInfos = orderInfo[i].split("\\|");
                Coupon[] productCouponList = new Coupon[productInfos.length-2];
                for (int j=2; j < productInfos.length ; j++ ) {
                    productCouponList[j-2] = new Coupon( couponList.get(productInfos[j]));
                }
                products[i-3] = new Product(productList.get(productInfos[0]), Integer.parseInt(productInfos[1]), productCouponList);
            }

            System.out.println("Basket ID is " + basketID);
        }
    }

    public void printBasket(){
        int thisPrice=0, salePrice=0, totalPrice=0, saleTotalPrice=0;
        String usedCoupon;
        for (Product thing : products) {
            thisPrice = thing.getPrice() * thing.getAmount();
            salePrice = thing.getPriceAdaptedCoupon();
            totalPrice += thisPrice;
            saleTotalPrice += salePrice;
            usedCoupon = "";
            for (Coupon cp : thing.getCoupons()) {
                if(cp.checkUsed()){
                    usedCoupon = usedCoupon+cp.getCode()+",";
                }
            }
            System.out.print(thing.getCode()+" ");
            System.out.print(thing.getAmount()+"EA ");
            System.out.print("Original Price "+thisPrice+" WON ");
            if(thisPrice != saleTotalPrice){
                System.out.print("Discount Price "+salePrice+" WON ");
                System.out.print("Adapted coupon : "+usedCoupon);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("total price "+totalPrice+" WON / total discount price "+saleTotalPrice+" WON\n");
        // System.out.println("WARNING");
        // System.out.println("========== TEST data area ==========");
        // System.out.println(totalPrice);
        // System.out.println(thisPrice);
        // System.out.println(salePrice);
        // System.out.println("========== TEST data area ==========");

    }
}
