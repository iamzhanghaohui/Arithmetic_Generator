import com.free.pojo.MyNumber;
import org.junit.Test;

/**
 * @ClassNameMyNumberTest
 * @Description
 * @Author Free
 * @Date2020/10/9 22:54
 * @Version V1.0
 **/
public class MyNumberTest {
    @Test
    public void MyNumber() {
        String result = "1'21/22";
        MyNumber fraction = new MyNumber(result);
        System.out.println(fraction);
        System.out.println(new MyNumber("2/6"));
        System.out.println(new MyNumber("-2/6"));
        System.out.println(new MyNumber("-6/6"));
        System.out.println(new MyNumber("-6/3"));

    }
}
