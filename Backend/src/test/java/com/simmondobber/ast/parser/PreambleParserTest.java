package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PreambleParserTest {

    @Test
    public void parser_should_parse_preamble() {
        //Given
        String stringToParse = "@Getter\nprivate final <T extends BaseEntity> @Setter next";
        String correctlyParsedString = "@Getter\nprivate final <T extends BaseEntity> @Setter ";
        PreambleParser preambleParser = new PreambleParser(new Pointer(stringToParse));

        //When
        Preamble parsedPreamble = preambleParser.parse();
        String parsedString = parsedPreamble.getSyntax();
        List<AstComponent> components = parsedPreamble.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Annotation.class, components.get(0));
        Assertions.assertInstanceOf(Keyword.class, components.get(1));
        Assertions.assertInstanceOf(Keyword.class, components.get(2));
        Assertions.assertInstanceOf(Generic.class, components.get(3));
        Assertions.assertInstanceOf(Annotation.class, components.get(4));
        Assertions.assertEquals("@Getter\n", components.get(0).getSyntax());
        Assertions.assertEquals("private ", components.get(1).getSyntax());
        Assertions.assertEquals("final ", components.get(2).getSyntax());
        Assertions.assertEquals("<T extends BaseEntity> ", components.get(3).getSyntax());
        Assertions.assertEquals("@Setter ", components.get(4).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_preamble_with_separators() {
        //Given
        String stringToParse = "@Getter \nprivate \n\n `123`\t final <T extends BaseEntity>  @Setter \tnext";
        String correctlyParsedString = "@Getter \nprivate \n\n `123`\t final <T extends BaseEntity>  @Setter \t";
        PreambleParser preambleParser = new PreambleParser(new Pointer(stringToParse));

        //When
        Preamble parsedPreamble = preambleParser.parse();
        String parsedString = parsedPreamble.getSyntax();
        List<AstComponent> components = parsedPreamble.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Annotation.class, components.get(0));
        Assertions.assertInstanceOf(Keyword.class, components.get(1));
        Assertions.assertInstanceOf(Keyword.class, components.get(2));
        Assertions.assertInstanceOf(Generic.class, components.get(3));
        Assertions.assertInstanceOf(Annotation.class, components.get(4));
        Assertions.assertEquals("@Getter \n", components.get(0).getSyntax());
        Assertions.assertEquals("private \n\n `123`\t ", components.get(1).getSyntax());
        Assertions.assertEquals("final ", components.get(2).getSyntax());
        Assertions.assertEquals("<T extends BaseEntity>  ", components.get(3).getSyntax());
        Assertions.assertEquals("@Setter \t", components.get(4).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_preamble() {
        //Given
        String stringToParse = "";
        String correctlyParsedString = "";
        PreambleParser preambleParser = new PreambleParser(new Pointer(stringToParse));

        //When
        Preamble parsedPreamble = preambleParser.parse();
        String parsedString = parsedPreamble.getSyntax();
        List<AstComponent> components = parsedPreamble.getChildAstComponents();

        //Then
        Assertions.assertEquals(0, components.size());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
