package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.ArrayBrackets;
import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.simpleComponentParser.PathParser;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.ArrayList;
import java.util.List;

public class TypeParser extends AstParser {

    public TypeParser(Pointer pointer) {
        super(pointer);
    }

    public TypeParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Type parse() {
        Path path = new PathParser(this.pointer).parse();
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
