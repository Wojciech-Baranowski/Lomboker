package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.AnnotationParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnnotationParserTest {

    @Test
    public void parser_should_parse_annotation() {
        //Given
        String stringToParse = "@Getter(value = AccessLevel.NONE)";
        String correctlyParsedString = "@Getter(value = AccessLevel.NONE)";
        AnnotationParser annotationParser = new AnnotationParser(new Pointer(stringToParse));

        //When
        Annotation parsedAnnotation = annotationParser.parse();
        String parsedString = parsedAnnotation.getSyntax();
        List<AstComponent> components = parsedAnnotation.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Name.class, components.get(1));
        Assertions.assertInstanceOf(Args.class, components.get(2));
        Assertions.assertEquals("@", components.get(0).getSyntax());
        Assertions.assertEquals("Getter", components.get(1).getSyntax());
        Assertions.assertEquals("(value = AccessLevel.NONE)", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_annotation_with_separators() {
        //Given
        String stringToParse = "@\n\t Getter  \n\n `123`\t(value = AccessLevel.NONE)  \t next";
        String correctlyParsedString = "@\n\t Getter  \n\n `123`\t(value = AccessLevel.NONE)  \t ";
        AnnotationParser annotationParser = new AnnotationParser(new Pointer(stringToParse));

        //When
        Annotation parsedAnnotation = annotationParser.parse();
        String parsedString = parsedAnnotation.getSyntax();
        List<AstComponent> components = parsedAnnotation.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Name.class, components.get(1));
        Assertions.assertInstanceOf(Args.class, components.get(2));
        Assertions.assertEquals("@\n\t ", components.get(0).getSyntax());
        Assertions.assertEquals("Getter  \n\n `123`\t", components.get(1).getSyntax());
        Assertions.assertEquals("(value = AccessLevel.NONE)  \t ", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_annotation_without_args() {
        //Given
        String stringToParse = "@Getter()";
        String correctlyParsedString = "@Getter()";
        AnnotationParser annotationParser = new AnnotationParser(new Pointer(stringToParse));

        //When
        Annotation parsedAnnotation = annotationParser.parse();
        String parsedString = parsedAnnotation.getSyntax();
        List<AstComponent> components = parsedAnnotation.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Name.class, components.get(1));
        Assertions.assertInstanceOf(Args.class, components.get(2));
        Assertions.assertEquals("@", components.get(0).getSyntax());
        Assertions.assertEquals("Getter", components.get(1).getSyntax());
        Assertions.assertEquals("()", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
