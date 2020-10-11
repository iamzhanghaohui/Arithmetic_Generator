import com.free.pojo.MyNumber;
import org.junit.Test;

/**
 * @author lucifer
 */
public class MyNumberTest {


    @Test
    public void addTest(){
        MyNumber left = new MyNumber(4,5);
        MyNumber right = new MyNumber(6,5);
        System.out.println(left.add(right));
    }

    @Test
    public void subtractTest(){
        MyNumber left = new MyNumber(7,5);
        MyNumber right = new MyNumber(6,5);
        System.out.println(left.subtract(right));
    }

    @Test
    public void multiplyTest(){
        MyNumber left = new MyNumber(4,5);
        MyNumber right = new MyNumber(6,5);
        System.out.println(left.multiply(right));
    }

    @Test
    public void divideTest(){
        MyNumber left = new MyNumber(4,5);
        MyNumber right = new MyNumber(6,5);
        System.out.println(left.divide(right));
    }


}
