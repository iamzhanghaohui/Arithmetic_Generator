import com.free.pojo.Expression;
import com.free.pojo.MyNumber;
import org.junit.Test;

/**
 * @ClassNameExpressionTest
 * @Description
 * @Author Free
 * @Date2020/10/9 22:58
 * @Version V1.0
 **/
public class ExpressionTest {

    @Test
    public void print() {
        for (int i = 0; i < 10; i++) {
            Expression expression = new Expression(1, 10);
            System.out.println(expression);
            System.out.println(expression.getRoot().result);
        }
    }

}
