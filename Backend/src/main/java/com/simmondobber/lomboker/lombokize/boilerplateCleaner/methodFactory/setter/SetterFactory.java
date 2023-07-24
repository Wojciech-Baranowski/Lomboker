package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setter;

import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.ArgsParser;
import com.simmondobber.ast.parser.complexComponentParser.PreambleParser;
import com.simmondobber.ast.parser.complexComponentParser.TypeParser;
import com.simmondobber.lomboker.common.Trimmer;

public class SetterFactory {

    private final String fieldType;
    private final String fieldName;

    public SetterFactory(Field field) {
        this.fieldType = Trimmer.trim(field.getType().getSyntax());
        this.fieldName = Trimmer.trim(field.getName().getSyntax());
    }

    public Method createSetter() {
        return createSetterMethod();
    }

    private Method createSetterMethod() {
        return new Method(
                createPreamble(),
                createType(),
                createName(),
                createArgs(),
                null,
                createMethodBody(createArgs()),
                null
        );
    }

    private Preamble createPreamble() {
        return new PreambleParser("public ").parse();
    }

    private Type createType() {
        return new TypeParser("void ").parse();
    }

    private Name createName() {
        String name = new SetterNameFactory(this.fieldType, this.fieldName).createSetterName();
        return new Name(name, "");
    }

    private Args createArgs() {
        String args = new SetterArgsFactory(this.fieldType, this.fieldName).createSetterArgs();
        return new ArgsParser(args).parse();
    }

    private MethodBody createMethodBody(Args args) {
        String bodySyntax = new StringBuilder()
                .append(" {\n\t")
                .append("this.")
                .append(this.fieldName)
                .append(" = ")
                .append(getArgName(args))
                .append(";\n")
                .append("}\n")
                .toString();
        return new MethodBody(bodySyntax, "");
    }

    private String getArgName(Args args) {
        String listingSyntax = args.getArgsListing().getSyntax();
        int nameIndex = listingSyntax.indexOf(" ") + 1;
        return listingSyntax.substring(nameIndex);
    }
}
