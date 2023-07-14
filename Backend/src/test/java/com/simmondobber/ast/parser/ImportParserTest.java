package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImportParserTest {

    @Test
    public void parser_should_parse_import() {
        //Given
        String stringToParse = "import com.simmondobber.ast.components.complexAstComponents.Import;";
        String correctlyParsedString = "import com.simmondobber.ast.components.complexAstComponents.Import;";
        ImportParser importParser = new ImportParser(new Pointer(stringToParse));

        //When
        Import parsedImport = importParser.parse();
        String parsedString = parsedImport.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_static_import() {
        //Given
        String stringToParse = "import static com.simmondobber.ast.components.complexAstComponents.Import;";
        String correctlyParsedString = "import static com.simmondobber.ast.components.complexAstComponents.Import;";
        ImportParser importParser = new ImportParser(new Pointer(stringToParse));

        //When
        Import parsedImport = importParser.parse();
        String parsedString = parsedImport.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_import_with_separators() {
        //Given
        String stringToParse = "import \n\n `123`\t com.simmondobber.ast.components.complexAstComponents.Import   ;\t next";
        String correctlyParsedString = "import \n\n `123`\t com.simmondobber.ast.components.complexAstComponents.Import   ;\t ";
        ImportParser importParser = new ImportParser(new Pointer(stringToParse));

        //When
        Import parsedImport = importParser.parse();
        String parsedString = parsedImport.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_static_import_with_separators() {
        //Given
        String stringToParse = "import  \t  static\n\n `123`\t com.simmondobber.ast.components.complexAstComponents.Import   ;\t next";
        String correctlyParsedString = "import  \t  static\n\n `123`\t com.simmondobber.ast.components.complexAstComponents.Import   ;\t ";
        ImportParser importParser = new ImportParser(new Pointer(stringToParse));

        //When
        Import parsedImport = importParser.parse();
        String parsedString = parsedImport.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
