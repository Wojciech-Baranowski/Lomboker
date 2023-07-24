package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.getter;

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

public class GetterFactoryTest {

    @ParameterizedTest
    @MethodSource("create_getter_test_data_provider")
    public void create_getter_test(String fieldType, String fieldName, String expectedMethodSyntax, String expectedMethodWithThisSyntax) {
        //Given
        Field field = create_test_field(fieldType, fieldName);
        GetterFactory getterFactory = new GetterFactory(field);

        //When
        GetterComponent createdGetterComponent = getterFactory.createGetter();

        //Then
        Assertions.assertEquals(field, createdGetterComponent.getField());
        Assertions.assertEquals(expectedMethodSyntax, createdGetterComponent.getMethod().getSyntax());
        Assertions.assertEquals(expectedMethodWithThisSyntax, createdGetterComponent.getMethodWithThis().getSyntax());
    }

    private static Stream<Arguments> create_getter_test_data_provider() {
        return Stream.of(
                Arguments.of("int", "point", "public int getPoint() {\n\treturn point;\n}\n", "public int getPoint() {\n\treturn this.point;\n}\n"),
                Arguments.of("int", "aPoint", "public int getAPoint() {\n\treturn aPoint;\n}\n", "public int getAPoint() {\n\treturn this.aPoint;\n}\n"),
                Arguments.of("int", "isPoint", "public int getIsPoint() {\n\treturn isPoint;\n}\n", "public int getIsPoint() {\n\treturn this.isPoint;\n}\n"),
                Arguments.of("int", "isPrivate", "public int getIsPrivate() {\n\treturn isPrivate;\n}\n", "public int getIsPrivate() {\n\treturn this.isPrivate;\n}\n"),
                Arguments.of("int", "isAbstract", "public int getIsAbstract() {\n\treturn isAbstract;\n}\n", "public int getIsAbstract() {\n\treturn this.isAbstract;\n}\n"),
                Arguments.of("boolean", "point", "public boolean isPoint() {\n\treturn point;\n}\n", "public boolean isPoint() {\n\treturn this.point;\n}\n"),
                Arguments.of("boolean", "aPoint", "public boolean isAPoint() {\n\treturn aPoint;\n}\n", "public boolean isAPoint() {\n\treturn this.aPoint;\n}\n"),
                Arguments.of("boolean", "isPoint", "public boolean isPoint() {\n\treturn isPoint;\n}\n", "public boolean isPoint() {\n\treturn this.isPoint;\n}\n"),
                Arguments.of("boolean", "isPrivate", "public boolean isPrivate() {\n\treturn isPrivate;\n}\n", "public boolean isPrivate() {\n\treturn this.isPrivate;\n}\n"),
                Arguments.of("boolean", "isAbstract", "public boolean isAbstract() {\n\treturn isAbstract;\n}\n", "public boolean isAbstract() {\n\treturn this.isAbstract;\n}\n"),
                Arguments.of("List<Integer>", "points", "public List<Integer> getPoints() {\n\treturn points;\n}\n", "public List<Integer> getPoints() {\n\treturn this.points;\n}\n"),
                Arguments.of("int[]", "points", "public int[] getPoints() {\n\treturn points;\n}\n", "public int[] getPoints() {\n\treturn this.points;\n}\n"),
                Arguments.of("List<Integer>[]", "points", "public List<Integer>[] getPoints() {\n\treturn points;\n}\n", "public List<Integer>[] getPoints() {\n\treturn this.points;\n}\n")
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
