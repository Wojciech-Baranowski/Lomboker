package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Throws;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.ThrowsListing;
import com.simmondobber.ast.parser.complexComponentParser.ThrowsParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ThrowsParserTest {

    @Test
    public void parser_should_parse_throws() {
        //Given
        String stringToParse = "throws NullPointerException, ValidatorException {";
        String correctlyParsedString = "throws NullPointerException, ValidatorException ";
        ThrowsParser throwsParser = new ThrowsParser(new Pointer(stringToParse));

        //When
        Throws parsedThrows = throwsParser.parse();
        String parsedString = parsedThrows.getSyntax();
        List<AstComponent> components = parsedThrows.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(ThrowsListing.class, components.get(1));
        Assertions.assertEquals("throws ", components.get(0).getSyntax());
        Assertions.assertEquals("NullPointerException, ValidatorException ", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_throws_with_separators() {
        //Given
        String stringToParse = "throws \n\n  \t NullPointerException, ValidatorException   `123` \t {";
        String correctlyParsedString = "throws \n\n  \t NullPointerException, ValidatorException   `123` \t ";
        ThrowsParser throwsParser = new ThrowsParser(new Pointer(stringToParse));

        //When
        Throws parsedThrows = throwsParser.parse();
        String parsedString = parsedThrows.getSyntax();
        List<AstComponent> components = parsedThrows.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(ThrowsListing.class, components.get(1));
        Assertions.assertEquals("throws \n\n  \t ", components.get(0).getSyntax());
        Assertions.assertEquals("NullPointerException, ValidatorException   `123` \t ", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
