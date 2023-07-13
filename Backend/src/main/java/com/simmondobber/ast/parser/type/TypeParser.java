package com.simmondobber.ast.parser.type;

import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.generic.GenericParser;

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
