package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.GenericExtension;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class GenericExtensionParser extends AstParser {

    public GenericExtensionParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public GenericExtension parse() {
        return new GenericExtension(this.pointer.getUntil(">"));
    }
}
