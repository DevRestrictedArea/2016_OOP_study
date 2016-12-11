/**
 * Coupon
 *
 * [Coupon Condition Code]
 * ONLYONE : Do not use any other coupon.
 * TARGET-[Product.code] : Only use for target product.
 * AMOUNT-[Number] : adapted product amount.
 * OVERCHARGE-[Number] : coupon can use when price is over than Number.
 * USERLIMIT-[Number] : limit use amount.
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

    /**
     * Instantiates a new Coupon.
     *
     * @param _type       the type
     * @param _code       the code
     * @param _name       the name
     * @param _price      the price
     * @param _conditions the conditions
     * @param _amount     the amount
     */
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

    /**
     * Instantiates a new Coupon for clone.
     *
     * @param _coupon the coupon
     */
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

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets discount price.
     *
     * @return the discount price
     */
    public int getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Gets discount rate.
     *
     * @return the discount rate
     */
    public int getDiscountRate() {
        return discountRate;
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
     * Get conditions string [ ].
     *
     * @return the string [ ]
     */
    public String[] getConditions() {
        return conditions;
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
     * Use coupon.
     */
    public void useCoupon() {
        isUsed = true;
        if( amount > 1){
            amount--;
        }
    }

    /**
     * Check used boolean.
     *
     * @return the boolean
     */
    public Boolean checkUsed(){
        return isUsed;
    }

    /**
     * Print value.
     */
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
