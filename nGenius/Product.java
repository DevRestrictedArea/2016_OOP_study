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

    public Product(String _code, int _price, String _name, String _description){
        code = _code;
        price = _price;
        name = _name;
        description = _description;
    }

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

    // clone contructor
    public Product(Product _product){
        code = _product.getCode();
        price = _product.getPrice();
        name = _product.getName();
        description = _product.getDescription();
        coupons = _product.getCoupons();
        amount = _product.getAmount();
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Coupon[] getCoupons() {
        return coupons;
    }

    public int getAmount() {
        return amount;
    }

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
    /*
        [Coupon Condition Code]
        ONLYONE : Do not use any other coupon.
        TARGET-[Product.code] : Only use for target product.
        AMOUNT-[Number] : adapted product amount.
        OVERCHARGE-[Number] : coupon can use when price is over than Number.
        USERLIMIT-[Number] : limit use amount.
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
                // System.out.println(conditionInfo[0]);
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
