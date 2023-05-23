package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.codeElement.ClassField;
import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import com.simmondobber.lomboker.lombokize.codeElement.MethodType;
import org.apache.commons.lang3.StringUtils;

import static com.simmondobber.lomboker.common.Keywords.*;

public class GetterFactory {

    private static final String GETTER_PREFIX = "get";
    private static final String BOOLEAN_GETTER_PREFIX = "is";

    public ClassMethod createGetter(ClassField classField) {
        String getterCode = createGetterCode(classField);
        return new ClassMethod(getterCode, classField.getLine(), MethodType.GETTER);
    }

    private String createGetterCode(ClassField classField) {
        return createGetterHead(classField) + createGetterBody(classField) + createClosingBracketLine(classField);
    }

    private String createGetterHead(ClassField classField) {
        String indentation = getIndentation(classField.getNesting());
        String type = classField.getFieldType();
        return StringUtils.join(indentation, PUBLIC.getKeyword(), " ", type, " ", getMethodName(classField), "() {\n");
    }

    private String createGetterBody(ClassField classField) {
        String indentation = getIndentation(classField.getNesting() + 1);
        String name = classField.getFieldName();
        return StringUtils.join(indentation, RETURN.getKeyword(), " ", THIS.getKeyword(), ".", name, ";\n");
    }

    private String createClosingBracketLine(ClassField classField) {
        String indentation = getIndentation(classField.getNesting());
        return StringUtils.join(indentation, "}\n");
    }

    private String getMethodName(ClassField classField) {
        String name = classField.getFieldName();
        String prefix = (classField.isBooleanType() ? isFieldNameStartingFromIs(name) ? "" : BOOLEAN_GETTER_PREFIX : GETTER_PREFIX);
        return prefix + ((isFieldNameStartingFromSingleSmallLetter(name) || isFieldNameStartingFromIs(name)) ? name : StringUtils.capitalize(name));
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
