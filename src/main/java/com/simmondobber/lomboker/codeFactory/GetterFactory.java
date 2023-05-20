package com.simmondobber.lomboker.codeFactory;

import com.simmondobber.lomboker.codeLine.ClassField;
import org.apache.commons.lang3.StringUtils;

import static com.simmondobber.lomboker.Keywords.PUBLIC;
import static com.simmondobber.lomboker.Keywords.THIS;

public class GetterFactory {

    private static final String GETTER_PREFIX = "get";
    private static final String BOOLEAN_GETTER_PREFIX = "is";
    private static final String INDENTATION = "\t";

    public String createGetterCode(ClassField classField) {
        return createGetterHead(classField) + createGetterBody(classField) + createClosingBracketLine(classField);
    }

    private String createGetterHead(ClassField classField) {
        String indentation = INDENTATION.repeat(classField.getNesting());
        String type = classField.getFieldType();
        return StringUtils.join(indentation, PUBLIC.getKeyword(), " ", type, " ", getMethodName(classField), "() {\n");
    }

    private String createGetterBody(ClassField classField) {
        String indentation = INDENTATION.repeat(classField.getNesting() + 1);
        String name = classField.getFieldName();
        return StringUtils.join(indentation, THIS.getKeyword(), ".", name, " = ", name, ";\n");
    }

    private String createClosingBracketLine(ClassField classField) {
        String indentation = INDENTATION.repeat(classField.getNesting());
        return StringUtils.join(indentation, "}\n");
    }

    private String getMethodName(ClassField classField) {
        String name = classField.getFieldName();
        String prefix = (classField.isBoolean() ? isFieldNameStartingFromIs(name) ? "" : BOOLEAN_GETTER_PREFIX : GETTER_PREFIX);
        return prefix + (isFieldNameStartingFromSingleSmallLetter(name) ? name : StringUtils.capitalize(name));
    }

    private boolean isFieldNameStartingFromIs(String fieldName) {
        return fieldName.split("[A-Z]")[0].equals(BOOLEAN_GETTER_PREFIX);
    }

    private boolean isFieldNameStartingFromSingleSmallLetter(String fieldName) {
        return fieldName.matches("[a-z][A-Z].*");
    }
}
