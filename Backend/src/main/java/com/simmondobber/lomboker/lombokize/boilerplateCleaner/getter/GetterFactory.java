package com.simmondobber.lomboker.lombokize.boilerplateCleaner.getter;

import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.ArgsParser;
import com.simmondobber.ast.parser.complexComponentParser.PreambleParser;
import com.simmondobber.ast.parser.complexComponentParser.TypeParser;
import com.simmondobber.lomboker.common.Trimmer;

public class GetterFactory {

    private final Field field;
    private final String fieldType;
    private final String fieldName;

    public GetterFactory(Field field) {
        this.field = field;
        this.fieldType = Trimmer.trim(field.getType().getSyntax());
        this.fieldName = Trimmer.trim(field.getName().getSyntax());
    }

    public GetterComponent createGetter() {
        Method getterMethod = createGetterMethod(false);
        Method getterMethodWithThis = createGetterMethod(true);
        return new GetterComponent(this.field, getterMethod, getterMethodWithThis);
    }

    private Method createGetterMethod(boolean thisPrefix) {
        return new Method(
                createPreamble(),
                createType(),
                createName(),
                createArgs(),
                null,
                createMethodBody(thisPrefix),
                null
        );
    }

    private Preamble createPreamble() {
        return new PreambleParser("public ").parse();
    }

    private Type createType() {
        return new TypeParser(this.fieldType + " ").parse();
    }

    private Name createName() {
        String name = new GetterNameFactory(this.fieldType, this.fieldName).createGetterName();
        return new Name(name, "");
    }

    private Args createArgs() {
        return new ArgsParser("() ").parse();
    }

    private MethodBody createMethodBody(boolean thisPrefix) {
        String bodySyntax = new StringBuilder()
                .append("{\n\t")
                .append("return ")
                .append(thisPrefix ? "this." : "")
                .append(this.fieldName)
                .append(";\n")
                .append("}\n")
                .toString();
        return new MethodBody(bodySyntax, "");
    }
}
