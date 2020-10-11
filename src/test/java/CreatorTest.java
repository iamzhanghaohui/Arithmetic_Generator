import com.free.pojo.Creator;
import org.junit.Test;

/**
 * @author lucifer
 */
public class CreatorTest {

    @Test
    public void getRandomInRangeTest(){
        System.out.println(Creator.getRandomInRange(4));
    }

    @Test
    public void generateMapTest(){
        System.out.println(Creator.generateMap(4,5));
    }
}
