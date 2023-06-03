package com.simmondobber.lomboker.lombokize.helpers.factories.lombokCompatibleFactories;

import com.simmondobber.lomboker.lombokize.classElements.Field;
import com.simmondobber.lomboker.lombokize.classElements.Method;
import com.simmondobber.lomboker.lombokize.classElements.MethodType;
import org.apache.commons.lang3.StringUtils;

import static com.simmondobber.lomboker.common.Keywords.*;

public class GetterFactory {

    private static final String GETTER_PREFIX = "get";
    private static final String BOOLEAN_GETTER_PREFIX = "is";

    public Method createGetter(Field field, boolean thisPrefix) {
        String getterCode = createGetterCode(field, thisPrefix);
        return new Method(getterCode, field.getLine(), MethodType.GETTER);
    }

    private String createGetterCode(Field field, boolean thisPrefix) {
        return createGetterHead(field) + createGetterBody(field, thisPrefix) + createClosingBracketLine(field);
    }

    private String createGetterHead(Field field) {
        String indentation = getIndentation(field.getNesting());
        String type = field.getFieldType();
        return StringUtils.join(indentation, PUBLIC.getKeyword(), " ", type, " ", getMethodName(field), "() {\n");
    }

    private String createGetterBody(Field field, boolean thisPrefix) {
        String indentation = getIndentation(field.getNesting() + 1);
        String name = field.getFieldName();
        String prefix = thisPrefix ? THIS.getKeyword() + "." : "";
        return StringUtils.join(indentation, RETURN.getKeyword(), " ", prefix, name, ";\n");
    }

    private String createClosingBracketLine(Field field) {
        String indentation = getIndentation(field.getNesting());
        return StringUtils.join(indentation, "}\n");
    }

    private String getMethodName(Field field) {
        String name = field.getFieldName();
        String prefix = (field.isBooleanType() ? isFieldNameStartingFromIs(name) ? "" : BOOLEAN_GETTER_PREFIX : GETTER_PREFIX);
        return prefix + (isFieldNameStartingFromIs(name) ? name : StringUtils.capitalize(name));
    }

    private boolean isFieldNameStartingFromIs(String fieldName) {
        return fieldName.split("[A-Z]")[0].equals(BOOLEAN_GETTER_PREFIX);
    }

    private String getIndentation(int nesting) {
        return " ".repeat(nesting * 4);
    }
}
