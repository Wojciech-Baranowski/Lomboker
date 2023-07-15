package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.utils.Pointer;

public class TypeParser extends AstParser {

    public TypeParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Type parse() {
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        Generic generic = null;
        if (this.pointer.lookupCharacter() == '<') {
            generic = new GenericParser(this.pointer).parse();
        }
        return new Type(name, generic);
    }
}
