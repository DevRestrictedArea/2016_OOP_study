package main.goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lasto on 2016-12-09.
 */
public class GoodsList {
    private Goods pcH = new Goods("H", 900000, "고급 게이밍 PC", "고사양 고급 PC");
    private Goods pcM = new Goods("M", 550000, "중급 게이밍 PC", "가성비 좋은 PC");
    private Goods pcL = new Goods("L", 350000, "알뜰 PC", "최소 구동환경 제공");

    public List<Goods> getList(){
        return getAllList();
    }

    private List<Goods> getAllList(){
        List<Goods> goodsList = new ArrayList<Goods>();

        goodsList.add(pcH);
        goodsList.add(pcM);
        goodsList.add(pcL);

        return goodsList;
    }


}
