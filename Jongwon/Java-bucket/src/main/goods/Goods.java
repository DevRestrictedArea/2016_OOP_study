package main.goods;

/**
 * Created by lasto on 2016-12-07.
 */
public class Goods {
    private String code;
    private int price;
    private String name;
    private String description;

    public Goods(String code, int price, String name, String description) {
        this.code = code;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }
}
