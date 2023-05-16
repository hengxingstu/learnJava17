import org.junit.jupiter.api.Test;

public class test01 {
    @Test
    public void test01() {
        for (int i = 0; i < 60; i++) {
            if (i % 3 == 0){
                System.out.println(i+". ");
            }
        }
    }
}
