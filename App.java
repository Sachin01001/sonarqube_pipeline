import java.math.BigDecimal;

public class Demo {

    public void test() {
        BigDecimal bd = new BigDecimal(0.1); // Bug: use BigDecimal.valueOf instead
        String a = "hello";
        if (a == "hello") { } // Code smell: == used on String
    }
}
