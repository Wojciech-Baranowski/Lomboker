package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.complexComponentParser.ImportParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<AstComponent> components = parsedImport.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(Path.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("import ", components.get(0).getSyntax());
        Assertions.assertEquals("com.simmondobber.ast.components.complexAstComponents.Import", components.get(1).getSyntax());
        Assertions.assertEquals(";", components.get(2).getSyntax());
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
        List<AstComponent> components = parsedImport.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(Keyword.class, components.get(1));
        Assertions.assertInstanceOf(Path.class, components.get(2));
        Assertions.assertInstanceOf(Character.class, components.get(3));
        Assertions.assertEquals("import ", components.get(0).getSyntax());
        Assertions.assertEquals("static ", components.get(1).getSyntax());
        Assertions.assertEquals("com.simmondobber.ast.components.complexAstComponents.Import", components.get(2).getSyntax());
        Assertions.assertEquals(";", components.get(3).getSyntax());
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
        List<AstComponent> components = parsedImport.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(Path.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("import \n\n `123`\t ", components.get(0).getSyntax());
        Assertions.assertEquals("com.simmondobber.ast.components.complexAstComponents.Import   ", components.get(1).getSyntax());
        Assertions.assertEquals(";\t ", components.get(2).getSyntax());
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
        List<AstComponent> components = parsedImport.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(Keyword.class, components.get(1));
        Assertions.assertInstanceOf(Path.class, components.get(2));
        Assertions.assertInstanceOf(Character.class, components.get(3));
        Assertions.assertEquals("import  \t  ", components.get(0).getSyntax());
        Assertions.assertEquals("static\n\n `123`\t ", components.get(1).getSyntax());
        Assertions.assertEquals("com.simmondobber.ast.components.complexAstComponents.Import   ", components.get(2).getSyntax());
        Assertions.assertEquals(";\t ", components.get(3).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
