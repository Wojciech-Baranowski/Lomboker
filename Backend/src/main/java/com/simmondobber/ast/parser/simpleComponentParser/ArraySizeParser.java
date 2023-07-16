package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.ArraySize;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ArraySizeParser extends AstParser {

    public ArraySizeParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ArraySize parse() {
        return new ArraySize(this.pointer.getUntil(']'), this.pointer.getSeparator());
    }
}
