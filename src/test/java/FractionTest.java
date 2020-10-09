import com.free.pojo.Fraction;
import org.junit.Test;

/**
 * @ClassNameFractionTest
 * @Description
 * @Author Free
 * @Date2020/10/9 22:54
 * @Version V1.0
 **/
public class FractionTest {
    @Test
    public void Fraction() {
        String result = "1'21/22";
        Fraction fraction = new Fraction(result);
        System.out.println(fraction);
        System.out.println(new Fraction("2/6"));
        System.out.println(new Fraction("-2/6"));
        System.out.println(new Fraction("-6/6"));
        System.out.println(new Fraction("-6/3"));

    }
}
