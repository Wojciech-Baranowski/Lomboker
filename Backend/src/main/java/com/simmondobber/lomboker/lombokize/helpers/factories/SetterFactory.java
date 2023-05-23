package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.common.Keywords;
import com.simmondobber.lomboker.lombokize.codeElement.ClassField;
import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import com.simmondobber.lomboker.lombokize.codeElement.MethodType;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static com.simmondobber.lomboker.common.Keywords.*;

public class SetterFactory {

    private static final String BOOLEAN_GETTER_PREFIX = "is";
    private static final String SETTER_PREFIX = "set";

    public ClassMethod createSetter(ClassField classField) {
        String setterCode = createSetterCode(classField);
        return new ClassMethod(setterCode, classField.getLine(), MethodType.SETTER);
    }

    private String createSetterCode(ClassField classField) {
        return createSetterHead(classField) + createSetterBody(classField) + createClosingBracketLine(classField);
    }

    private String createSetterHead(ClassField classField) {
        String indentation = getIndentation(classField.getNesting());
        String type = classField.getFieldType();
        return StringUtils.join(indentation, PUBLIC.getKeyword(), " ", VOID.getKeyword(), " ", getMethodName(classField), "(", type, " ", getArgumentName(classField), ") {\n");
    }

    private String createSetterBody(ClassField classField) {
        String indentation = getIndentation(classField.getNesting() + 1);
        String name = classField.getFieldName();
        String argumentName = getArgumentName(classField);
        return StringUtils.join(indentation, THIS.getKeyword(), ".", name, " = ", argumentName, ";\n");
    }

    private String createClosingBracketLine(ClassField classField) {
        String indentation = getIndentation(classField.getNesting());
        return StringUtils.join(indentation, "}\n");
    }

    private String getMethodName(ClassField classField) {
        String name = classField.getFieldName();
        return SETTER_PREFIX + ((isFieldBooleanStartingFromIs(classField)) ? name.substring(2) : isFieldNameStartingFromSingleSmallLetter(name) ? name : StringUtils.capitalize(name));
    }

    private String getArgumentName(ClassField classField) {
        String name = classField.getFieldName();
        return (classField.isBooleanType() && isFieldNameStartingFromIs(name)) ? getBooleanIsArgumentName(name) : name;
    }

    private String getBooleanIsArgumentName(String name) {
        name = name.substring(2);
        return (isFieldNameKeyword(name) ? "a" + name : name.toLowerCase());
    }

    private boolean isFieldNameKeyword(String fieldName) {
        return Arrays.stream(Keywords.values())
                .map(Keywords::getKeyword)
                .anyMatch(kw -> kw.equals(fieldName.toLowerCase()));
    }

    private boolean isFieldBooleanStartingFromIs(ClassField classField) {
        return classField.isBooleanType() && isFieldNameStartingFromIs(classField.getFieldName());
    }

    private boolean isFieldNameStartingFromIs(String fieldName) {
        return fieldName.split("[A-Z]")[0].equals(BOOLEAN_GETTER_PREFIX);
    }

    private boolean isFieldNameStartingFromSingleSmallLetter(String fieldName) {
        return fieldName.matches("[a-z][A-Z].*");
    }

    private String getIndentation(int nesting) {
        return " ".repeat(nesting * 4);
    }
}
