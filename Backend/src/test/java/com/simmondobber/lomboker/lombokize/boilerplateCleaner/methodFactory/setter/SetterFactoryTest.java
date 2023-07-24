package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setter;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.PreambleParser;
import com.simmondobber.ast.parser.complexComponentParser.TypeParser;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.NameParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SetterFactoryTest {

    @ParameterizedTest
    @MethodSource("create_setter_test_data_provider")
    public void create_setter_test(String fieldType, String fieldName, String expectedMethodSyntax) {
        //Given
        Field field = create_test_field(fieldType, fieldName);
        SetterFactory setterFactory = new SetterFactory(field);

        //When
        SetterComponent createdSetterComponent = setterFactory.createSetter();

        //Then
        Assertions.assertEquals(field, createdSetterComponent.getField());
        Assertions.assertEquals(expectedMethodSyntax, createdSetterComponent.getMethod().getSyntax());
    }

    private static Stream<Arguments> create_setter_test_data_provider() {
        return Stream.of(
                Arguments.of("int", "point", "public int setPoint(int point) {\n\tthis.point = point;\n}\n"),
                Arguments.of("int", "aPoint", "public int setAPoint(int aPoint) {\n\tthis.aPoint = aPoint;\n}\n"),
                Arguments.of("int", "isPoint", "public int setIsPoint(int isPoint) {\n\tthis.isPoint = isPoint;\n}\n"),
                Arguments.of("int", "isPrivate", "public int setIsPrivate(int isPrivate) {\n\tthis.isPrivate = isPrivate;\n}\n"),
                Arguments.of("int", "isAbstract", "public int setIsAbstract(int isAbstract) {\n\tthis.isAbstract = isAbstract;\n}\n"),
                Arguments.of("boolean", "point", "public boolean setPoint(boolean point) {\n\tthis.point = point;\n}\n"),
                Arguments.of("boolean", "aPoint", "public boolean setAPoint(boolean aPoint) {\n\tthis.aPoint = aPoint;\n}\n"),
                Arguments.of("boolean", "isPoint", "public boolean setPoint(boolean point) {\n\tthis.isPoint = point;\n}\n"),
                Arguments.of("boolean", "isPrivate", "public boolean setPrivate(boolean aPrivate) {\n\tthis.isPrivate = aPrivate;\n}\n"),
                Arguments.of("boolean", "isAbstract", "public boolean setAbstract(boolean anAbstract) {\n\tthis.isAbstract = anAbstract;\n}\n"),
                Arguments.of("List<Integer>", "points", "public List<Integer> setPoints(List<Integer> points) {\n\tthis.points = points;\n}\n"),
                Arguments.of("int[]", "points", "public int[] setPoints(int[] points) {\n\tthis.points = points;\n}\n"),
                Arguments.of("List<Integer>[]", "points", "public List<Integer>[] setPoints(List<Integer>[] points) {\n\tthis.points = points;\n}\n")
        );
    }

    private Field create_test_field(String fieldType, String fieldName) {
        Preamble preamble = new PreambleParser("public").parse();
        Type type = new TypeParser(fieldType).parse();
        Name name = new NameParser(fieldName).parse();
        Character semicolon = new CharacterParser(";").parse();
        return new Field(preamble, type, name, null, semicolon);
    }
}
