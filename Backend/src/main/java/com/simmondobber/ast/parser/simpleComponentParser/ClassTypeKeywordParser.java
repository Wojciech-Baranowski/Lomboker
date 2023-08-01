package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.ClassTypeKeyword;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ClassTypeKeywordParser extends AstParser {

    public ClassTypeKeywordParser(Pointer pointer) {
        super(pointer);
    }

    public ClassTypeKeywordParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public ClassTypeKeyword parse() {
        return new ClassTypeKeyword(this.pointer.getWord(), this.pointer.getLastSeparator(), this.pointer.getSeparator());
    }
}
