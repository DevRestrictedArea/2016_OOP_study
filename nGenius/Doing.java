/**
 * Doing
 */

import java.util.HashMap;

public class Doing {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // before test setting value
        HashMap<String , Product> productList = new HashMap<String , Product>();
        HashMap<String , Coupon> couponList = new HashMap<String , Coupon>();
        HashMap<String , Order> orderList = new HashMap<String , Order>();

        String[] conditions1 = {"TARGET-L", "AMOUNT-1", "ONLYONE"};
        String[] conditions2 = {"TARGET-L", "AMOUNT-10", "ONLYONE"};
        String[] conditions3 = {"OVERCHARGE-300000", "ONLYONE"};
        String[] conditions4 = {"USERLIMIT-10"};

        productList.put("H",new Product("H", 900000, "high-end Gaming PC", "high-end PC"));
        productList.put("M", new Product("M", 550000, "Midrange Gaming PC", "economical PC"));
        productList.put("L", new Product("L", 350000, "Cheep PC", "lowest enverment PC"));

        couponList.put("C1", new Coupon("PRODUCT", "C1", "Cheep PC sale coupon", "50000", conditions1, -1));
        couponList.put("C1-1", new Coupon("PRODUCT", "C1-1", "Cheep PC sale coupon2", "40000", conditions2, -1));
        couponList.put("C2", new Coupon("BASKET", "C2", "Basket coupon", "10%", conditions3, -1));
        couponList.put("C3", new Coupon("BASKET", "C3", "first come sale coupon", "20%", conditions4, 10));


        /**
         * Test for Product.java
         */
        System.out.println("====== test for Product.java ======");
        productList.get("H").printValue();
        productList.get("M").printValue();
        productList.get("L").printValue();

        System.out.println("\n");

        /**
         * test for Coupon.java
         */
        System.out.println("====== test for Coupon.java ======");
        couponList.get("C1").printValue();
        couponList.get("C1-1").printValue();
        couponList.get("C2").printValue();
        couponList.get("C3").printValue();

        System.out.println("\n");

        /**
         * test for Order.java
         */
        System.out.println("====== test for Order.java ======");
        String orderString = "123456,tmon,C3,H|1|C2";
        orderList.put(orderString.split(",")[0], new Order(orderString, productList, couponList) );
        orderString = "123457,tmon2,,L|2|C1,H|1";
        orderList.put(orderString.split(",")[0], new Order(orderString, productList, couponList) );
        orderString = "123458,tmon3,C3|C4,H|1,M|1,L|1";
        orderList.put(orderString.split(",")[0], new Order(orderString, productList, couponList) );

        orderList.get("123456").printBasket();
        orderList.get("123457").printBasket();

        System.out.println("");

    }
}
