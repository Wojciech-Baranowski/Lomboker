package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class MethodBodyParser extends AstParser {

    public MethodBodyParser(Pointer pointer) {
        super(pointer);
    }

    public MethodBodyParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public MethodBody parse() {
        return new MethodBody(this.pointer.getInsideInclusive('}'), this.pointer.getSeparator());
    }
}
