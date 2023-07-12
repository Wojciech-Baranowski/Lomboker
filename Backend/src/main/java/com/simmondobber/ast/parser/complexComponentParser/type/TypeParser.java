package com.simmondobber.ast.parser.complexComponentParser.type;

import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.complexComponentParser.generic.GenericParser;

public class TypeParser extends AstParser {

    public TypeParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Type parse() {
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        Generic generic = new GenericParser(this.pointer).parse();
        return new Type(name, generic);
    }
}
