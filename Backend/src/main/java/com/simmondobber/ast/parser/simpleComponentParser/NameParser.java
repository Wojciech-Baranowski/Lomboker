package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class NameParser extends AstParser {

    public NameParser(Pointer pointer) {
        super(pointer);
    }

    public NameParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Name parse() {
        return new Name(this.pointer.getWord(), this.pointer.getLastSeparator(), this.pointer.getSeparator());
    }
}
