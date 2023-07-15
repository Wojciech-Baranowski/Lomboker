package externalTests;

import com.simmondobber.ast.Ast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExternalTests {

    @Test
    public void test() {
        //Given
        List<String> fileCodes = new TestFilesLoader("testFileCollection").loadTestFiles();

        for (String fileCode : fileCodes) {
            //When
            Ast ast = new Ast(fileCode);

            //Then
            Assertions.assertEquals(fileCode, ast.getCode());
        }
    }
}
