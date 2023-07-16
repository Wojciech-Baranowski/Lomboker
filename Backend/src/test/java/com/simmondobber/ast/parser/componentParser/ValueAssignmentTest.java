package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.complexComponentParser.ValueAssignmentParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ValueAssignmentTest {

    @Test
    public void parser_should_parse_value_assignment() {
        //Given
        String stringToParse = "= 123;";
        String correctlyParsedString = "= 123";
        ValueAssignmentParser valueAssignmentParser = new ValueAssignmentParser(new Pointer(stringToParse));

        //When
        ValueAssignment parsedValueAssignment = valueAssignmentParser.parse();
        String parsedString = parsedValueAssignment.getSyntax();
        List<AstComponent> components = parsedValueAssignment.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Value.class, components.get(1));
        Assertions.assertEquals("= ", components.get(0).getSyntax());
        Assertions.assertEquals("123", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_value_assignment_with_separators() {
        //Given
        String stringToParse = "= 123  \n\n `123`\t  ;";
        String correctlyParsedString = "= 123  \n\n `123`\t  ";
        ValueAssignmentParser valueAssignmentParser = new ValueAssignmentParser(new Pointer(stringToParse));

        //When
        ValueAssignment parsedValueAssignment = valueAssignmentParser.parse();
        String parsedString = parsedValueAssignment.getSyntax();
        List<AstComponent> components = parsedValueAssignment.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Value.class, components.get(1));
        Assertions.assertEquals("= ", components.get(0).getSyntax());
        Assertions.assertEquals("123  \n\n `123`\t  ", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_lambda_value_assignment() {
        //Given
        String stringToParse = "= (x) -> {\nSystem.out.println(x);\n};";
        String correctlyParsedString = "= (x) -> {\nSystem.out.println(x);\n}";
        ValueAssignmentParser valueAssignmentParser = new ValueAssignmentParser(new Pointer(stringToParse));

        //When
        ValueAssignment parsedValueAssignment = valueAssignmentParser.parse();
        String parsedString = parsedValueAssignment.getSyntax();
        List<AstComponent> components = parsedValueAssignment.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Value.class, components.get(1));
        Assertions.assertEquals("= ", components.get(0).getSyntax());
        Assertions.assertEquals("(x) -> {\nSystem.out.println(x);\n}", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

}
