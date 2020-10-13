import com.free.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassNameCheckTest
 * @Description
 * @Author Free
 * @Date2020/10/10 17:15
 * @Version V1.0
 **/
public class CheckTest {
    @Test
    public void fileTest()throws IOException {
        File answerFile=new File("D:\\Free\\课程\\软件工程\\arithmetic-generate\\answerfile.txt");
        File exerciseFile=new File("D:\\Free\\课程\\软件工程\\arithmetic-generate\\answerfile.txt");
        FileUtils.compare(answerFile,exerciseFile);
    }
}
