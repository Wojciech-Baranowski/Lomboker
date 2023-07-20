package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ValueParser extends AstParser {

    public ValueParser(Pointer pointer) {
        super(pointer);
    }

    public ValueParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Value parse() {
        return new Value(this.pointer.getInside(';'));
    }
}
