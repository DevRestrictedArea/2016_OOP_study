package test;
/**
 * Created by lasto on 2016-12-07.
 */

import junit.framework.*;
import main.Parser;

public class OrderTest extends TestCase {

    public void testOrder() throws Exception {
        Parser parser = new Parser();
        String order1 = "123456,tmon,C3,H|1|C2";
        String order2 = "123457,tmon2,,L|2|C1,H|1";
        String order3 = "123457,tmon3,C3|C4,H|1,M|1,L|1";

        parser.parseInput(order1);
        parser.parseInput(order2);
        parser.parseInput(order3);
    }

    public void testVariable() throws Exception {
        Parser parser = new Parser();
        String order1 = "123456,tmon,C3,H|1|C2";
        String order2 = "123457,tmon2,,L|2|C1,H|1";
        String order3 = "123457,tmon3,C3|C4,H|1,M|1,L|1";

        parser.parseVariable(order1);
        parser.parseVariable(order2);
        parser.parseVariable(order3);
    }

    public void testSplit() throws Exception {

        Parser parser = new Parser();
        String order1 = "H|1|C2";
        String order2 = "L|2|C1,H|1";
        String order3 = "C3|C4,H|1,M|1,L|1";

        parser.splitBar(order1);
        parser.splitBar(order2);
        parser.splitBar(order3);

    }
}