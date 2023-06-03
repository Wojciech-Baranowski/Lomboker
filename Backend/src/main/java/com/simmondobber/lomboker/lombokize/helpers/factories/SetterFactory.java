package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.common.Keywords;
import com.simmondobber.lomboker.lombokize.classElements.Field;
import com.simmondobber.lomboker.lombokize.classElements.Method;
import com.simmondobber.lomboker.lombokize.classElements.MethodType;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static com.simmondobber.lomboker.common.Keywords.*;

public class SetterFactory {

    private static final String VOWELS = "AEIOU";
    private static final String BOOLEAN_GETTER_PREFIX = "is";
    private static final String SETTER_PREFIX = "set";

    public Method createSetter(Field field) {
        String setterCode = createSetterCode(field);
        return new Method(setterCode, field.getLine(), MethodType.SETTER);
    }

    private String createSetterCode(Field field) {
        return createSetterHead(field) + createSetterBody(field) + createClosingBracketLine(field);
    }

    private String createSetterHead(Field field) {
        String indentation = getIndentation(field.getNesting());
        String type = field.getFieldType();
        return StringUtils.join(indentation, PUBLIC.getKeyword(), " ", VOID.getKeyword(), " ", getMethodName(field), "(", type, " ", getArgumentName(field), ") {\n");
    }

    private String createSetterBody(Field field) {
        String indentation = getIndentation(field.getNesting() + 1);
        String name = field.getFieldName();
        String argumentName = getArgumentName(field);
        return StringUtils.join(indentation, THIS.getKeyword(), ".", name, " = ", argumentName, ";\n");
    }

    private String createClosingBracketLine(Field field) {
        String indentation = getIndentation(field.getNesting());
        return StringUtils.join(indentation, "}\n");
    }

    private String getMethodName(Field field) {
        String name = field.getFieldName();
        return SETTER_PREFIX + ((isFieldBooleanStartingFromIs(field)) ? name.substring(2) : isFieldNameStartingFromSingleSmallLetter(name) ? name : StringUtils.capitalize(name));
    }

    private String getArgumentName(Field field) {
        String name = field.getFieldName();
        return (field.isBooleanType() && isFieldNameStartingFromIs(name)) ? getBooleanIsArgumentName(name) : name;
    }

    private String getBooleanIsArgumentName(String name) {
        name = name.substring(2);
        return (isFieldNameKeyword(name) ? getArgumentForKeywordFieldName(name) : name.toLowerCase());
    }

    private String getArgumentForKeywordFieldName(String name) {
        String firstLetterOfName = String.valueOf(name.charAt(0));
        String prefix = (VOWELS.contains(firstLetterOfName)) ? "an" : "a";
        return prefix + name;
    }

    private boolean isFieldNameKeyword(String fieldName) {
        return Arrays.stream(Keywords.values())
                .map(Keywords::getKeyword)
                .anyMatch(kw -> kw.equals(fieldName.toLowerCase()));
    }

    private boolean isFieldBooleanStartingFromIs(Field field) {
        return field.isBooleanType() && isFieldNameStartingFromIs(field.getFieldName());
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
