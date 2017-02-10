package main;

/**
 * Created by lasto on 2016-12-08.
 */
public class Parser {

    private final String regex = "(\\d{6}),(\\w{4,12}),([^,]*),(.*)";

    public String[][] splitBar(String info){
        String[] commaItem = info.split(",");
        String[][] result = new String[commaItem.length][];
        for (int i=0;i< commaItem.length;i++){
            result[i] = commaItem[i].split("\\|");
        }
        return result;
    }

    public String[] parseVariable(String query){
        String[] item = {"$1","$2","$3","$4"};
        String[] info = new String[4];

        for(int i=0;i<item.length;i++) {
            info[i] = query.replaceAll(regex, item[i]);
        }

        return info;
    }

    public boolean parseInput(String query) {
        boolean tf = query.matches(regex);
        assert tf : "Condition Error: " + query;
        return tf;
    }
}
