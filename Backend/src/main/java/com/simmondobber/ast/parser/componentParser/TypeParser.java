package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.ArrayBrackets;
import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.ArrayList;
import java.util.List;

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
        List<ArrayBrackets> arrayBrackets = new ArrayList<>();
        while (this.pointer.lookupCharacter() == '[') {
            arrayBrackets.add(new ArrayBracketsParser(this.pointer).parse());
        }
        return new Type(path, generic, arrayBrackets);
    }
}
