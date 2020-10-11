import com.free.pojo.Check;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author lucifer
 */
public class AnswerCheckTest {

    @Test
    public void answerCheck() throws IOException {
        String answerFile = "answerfile.txt";
        String exerciseAnswerFile = "answerfile2.txt";
        File answer = new File(answerFile);
        File exerciseAnswer = new File(exerciseAnswerFile);
        Check.checkAnswer(answer,exerciseAnswer);
    }
}
