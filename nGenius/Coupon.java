/**
 * Coupon
 */
/*
    [Coupon Condition Code]
    ONLYONE : Do not use any other coupon.
    TARGET-[Product.code] : Only use for target product.
    AMOUNT-[Number] : adapted product amount.
    OVERCHARGE-[Number] : coupon can use when price is over than Number.
    USERLIMIT-[Number] : limit use amount.
*/
public class Coupon {

    private String type;
    private String code;
    private String name;
    private int discountPrice;
    private int discountRate;
    private String[] conditions;
    private Boolean isUsed = false;
    private static int amount = -1;

    public Coupon (String _type, String _code, String _name, String _price, String[] _conditions, int _amount) {
        type = _type;
        code = _code;
        name = _name;
        conditions = _conditions;
        if(_price.indexOf('%') == -1){
            // _price is price
            discountPrice = Integer.parseInt(_price);
            discountRate = -1;
        } else {
            // _price is rate
            discountPrice = -1;
            discountRate = Integer.parseInt(_price.replaceAll("\\D+",""));
        }
        if(_amount != 0 && _amount > 1){
            amount = _amount;
        }
    }

    // clone contructor
    public Coupon ( Coupon _coupon ) {
        this.type = _coupon.type;
        this.code = _coupon.code;
        this.name = _coupon.name;
        this.conditions = _coupon.conditions;
        this.discountPrice = _coupon.discountPrice;
        this.discountRate = _coupon.discountRate;
        this.conditions = _coupon.conditions.clone();
        this.amount = _coupon.amount;
    }

    public String getCode() {
        return code;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
    public int getDiscountRate() {
        return discountRate;
    }

    public String getName() {
        return name;
    }

    public String[] getConditions() {
        return conditions;
    }

    public int getAmount() {
        return amount;
    }

    public void useCoupon() {
        isUsed = true;
        if( amount > 1){
            amount--;
        }
    }

    public Boolean checkUsed(){
        return isUsed;
    }

    public void printValue() {
        System.out.println("code: " + code);
        System.out.println("name: " + name);
        System.out.println("price: " + getDiscountPrice());
        System.out.println("rate: " + getDiscountRate());
        for (String condition : conditions) {
            System.out.println("condition: " + condition);
        }
        System.out.println("");
    }
}
