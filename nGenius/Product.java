/**
 * Product - Product infomation
 */
public class Product {
    private String code;
    private int price;
    private String name;
    private String description;
    private Coupon[] coupons;
    private int amount = 1;

    /**
     * Instantiates a new Product.
     *
     * @param _code        the code
     * @param _price       the price
     * @param _name        the name
     * @param _description the description
     */
    public Product(String _code, int _price, String _name, String _description){
        code = _code;
        price = _price;
        name = _name;
        description = _description;
    }

    /**
     * Instantiates a new Product.
     *
     * @param _product the product
     * @param _amount  the amount
     * @param _coupons the coupons
     */
    public Product(Product _product, int _amount, Coupon[] _coupons){
        code = _product.getCode();
        price = _product.getPrice();
        name = _product.getName();
        description = _product.getDescription();
        coupons = new Coupon[_coupons.length];
        coupons = _coupons;
        if(_amount > 0){
            amount = _amount;
        }
    }

    /**
     * Instantiates a new Product for clone.
     *
     * @param _product the product
     */
    public Product(Product _product){
        code = _product.getCode();
        price = _product.getPrice();
        name = _product.getName();
        description = _product.getDescription();
        coupons = _product.getCoupons();
        amount = _product.getAmount();
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get coupons coupon [ ].
     *
     * @return the coupon [ ]
     */
    public Coupon[] getCoupons() {
        return coupons;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Print value.
     */
    public void printValue() {
        System.out.println("code: " + code);
        System.out.println("price: " + price);
        System.out.println("name: " + name);
        System.out.println("description: " + description);
        if(coupons != null){
            for (Coupon c : coupons) {
                c.printValue();
            }
        }
        System.out.println("amount: " + amount);
        System.out.println("");
    }

    /**
     * Get price adapted coupon int.
     *
     *  [Coupon Condition Code]
     *  ONLYONE : Do not use any other coupon.
     *  TARGET-[Product.code] : Only use for target product.
     *  AMOUNT-[Number] : adapted product amount.
     *  OVERCHARGE-[Number] : coupon can use when price is over than Number.
     *  USERLIMIT-[Number] : limit use amount.
     *
     * @return the int
     */

    public int getPriceAdaptedCoupon(){
        if(coupons == null){
            return this.price * this.amount;
        }

        int adaptedPrice = this.price;
        int adaptedAmount = this.amount;
        int remainAmount = 0;
        Boolean onlyuse = false;
        int discountPrice = 0;
        int discountRate = 100;

        for(Coupon cp : coupons){
            if( cp.getAmount() == 0){
                continue;
            }
            String[] couponConditions = cp.getConditions();
            discountPrice = cp.getDiscountPrice();
            discountRate = 100 - cp.getDiscountRate();
            if(discountRate == 101){
                discountRate = 100;
            }
            for (String condition : couponConditions) {
                String[] conditionInfo = condition.split("-");
                switch (conditionInfo[0]) {
                    case "USERLIMIT":
                    if(cp.getAmount() < 1){
                        return this.price * this.amount;
                    } else {
                        if(discountPrice != -1){
                            adaptedPrice = this.price - discountPrice;
                        }
                    }
                    break;
                    case "ONLYONE" :
                        if(onlyuse == false){
                            onlyuse = true;
                        } else {
                            cp.useCoupon();
                            return (adaptedAmount * adaptedPrice) + (remainAmount * this.price) * (discountRate / 100);
                        }
                    break;
                    case "AMOUNT" :
                    if(this.amount > Integer.parseInt(conditionInfo[1])){
                        adaptedAmount = Integer.parseInt(conditionInfo[1]);
                        remainAmount = this.amount - Integer.parseInt(conditionInfo[1]);
                    }
                    break;
                    case "OVERCHARGE" :
                    if( (this.price * this.amount) > Integer.parseInt(conditionInfo[1])){
                        if(discountPrice != -1){
                            adaptedPrice = discountPrice;
                        }
                    }
                    break;
                    case "TARGET" :
                    if(!conditionInfo[1].equals(code)){
                        return this.price * this.amount;
                    } else {
                        if(discountPrice != 100){
                            adaptedPrice = discountPrice;
                        }
                    }
                }
            }
            if (adaptedPrice != this.price || discountRate != 100) {
                cp.useCoupon();
            }
        }
        return ((adaptedAmount * adaptedPrice) + (remainAmount * this.price)) * discountRate / 100;

    }
}
