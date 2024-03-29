package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.PreambleParser;
import com.simmondobber.ast.parser.complexComponentParser.TypeParser;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.NameParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class MethodFactoryTest {

    @Test
    public void create_all_methods_based_on_field_test() {
        //Given
        List<Field> classFields = create_class_field_list();
        MethodFactory methodFactory = new MethodFactory();
        List<String> methodsSyntax = get_methods_syntax().stream().sorted().toList();

        //When
        List<Method> generatedMethods = methodFactory.generateMethodsBasedOnClassFields(classFields);
        List<String> generatedMethodsSyntax = generatedMethods.stream()
                .map(Method::getFullSyntax)
                .sorted()
                .toList();

        //Then
        Assertions.assertEquals(methodsSyntax.size(), generatedMethods.size());
        for (int i = 0; i < generatedMethodsSyntax.size(); i++) {
            Assertions.assertEquals(methodsSyntax.get(i), generatedMethodsSyntax.get(i));
        }
    }

    private Field create_test_field(String fieldType, String fieldName) {
        Preamble preamble = new PreambleParser("public").parse();
        Type type = new TypeParser(fieldType).parse();
        Name name = new NameParser(fieldName).parse();
        Character semicolon = new CharacterParser(";").parse();
        return new Field(preamble, type, name, null, semicolon);
    }

    private List<String> get_methods_syntax() {
        return Stream.concat(get_getters_syntax().stream(), get_setters_syntax().stream()).toList();
    }

    private List<Field> create_class_field_list() {
        Field field1 = create_test_field("int", "point");
        Field field2 = create_test_field("boolean", "point");
        Field field3 = create_test_field("int", "isPoint");
        Field field4 = create_test_field("boolean", "isPoint");
        return List.of(field1, field2, field3, field4);
    }

    private List<String> get_getters_syntax() {
        return List.of(
                "public int getPoint() {\n\treturn point;\n}\n", "public int getPoint() {\n\treturn this.point;\n}\n",
                "public boolean isPoint() {\n\treturn point;\n}\n", "public boolean isPoint() {\n\treturn this.point;\n}\n",
                "public int getIsPoint() {\n\treturn isPoint;\n}\n", "public int getIsPoint() {\n\treturn this.isPoint;\n}\n",
                "public boolean isPoint() {\n\treturn isPoint;\n}\n", "public boolean isPoint() {\n\treturn this.isPoint;\n}\n"
        );
    }

    private List<String> get_setters_syntax() {
        return List.of(
                "public void setPoint(int point) {\n\tpoint = point;\n}\n", "public void setPoint(int point) {\n\tthis.point = point;\n}\n",
                "public void setPoint(boolean point) {\n\tpoint = point;\n}\n", "public void setPoint(boolean point) {\n\tthis.point = point;\n}\n",
                "public void setIsPoint(int isPoint) {\n\tisPoint = isPoint;\n}\n", "public void setIsPoint(int isPoint) {\n\tthis.isPoint = isPoint;\n}\n",
                "public void setPoint(boolean point) {\n\tisPoint = point;\n}\n", "public void setPoint(boolean point) {\n\tthis.isPoint = point;\n}\n"
        );
    }
}
