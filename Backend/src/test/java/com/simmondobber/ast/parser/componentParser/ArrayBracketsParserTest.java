package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.ArrayBrackets;
import com.simmondobber.ast.components.simpleAstComponents.ArraySize;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.complexComponentParser.ArrayBracketsParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ArrayBracketsParserTest {

    @Test
    public void parser_should_parse_array_brackets() {
        //Given
        String stringToParse = "[10]";
        String correctlyParsedString = "[10]";
        ArrayBracketsParser arrayBracketsParser = new ArrayBracketsParser(new Pointer(stringToParse));

        //When
        ArrayBrackets parsedArrayBrackets = arrayBracketsParser.parse();
        String parsedString = parsedArrayBrackets.getSyntax();
        List<AstComponent> components = parsedArrayBrackets.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(ArraySize.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("[", components.get(0).getSyntax());
        Assertions.assertEquals("10", components.get(1).getSyntax());
        Assertions.assertEquals("]", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_array_brackets_with_separators() {
        //Given
        String stringToParse = "[\n10   ]\t`1`";
        String correctlyParsedString = "[\n10   ]\t`1`";
        ArrayBracketsParser arrayBracketsParser = new ArrayBracketsParser(new Pointer(stringToParse));

        //When
        ArrayBrackets parsedArrayBrackets = arrayBracketsParser.parse();
        String parsedString = parsedArrayBrackets.getSyntax();
        List<AstComponent> components = parsedArrayBrackets.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(ArraySize.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("[\n", components.get(0).getSyntax());
        Assertions.assertEquals("10   ", components.get(1).getSyntax());
        Assertions.assertEquals("]\t`1`", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
