package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.utils.Pointer;

public class TypeParser extends AstParser {

    public TypeParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Type parse() {
        Path path = new Path(this.pointer.getCompoundWord(), this.pointer.getSeparator());
        Generic generic = null;
        if (this.pointer.lookupCharacter() == '<') {
            generic = new GenericParser(this.pointer).parse();
        }
        return new Type(path, generic);
    }
}
