package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class KeywordParser extends AstParser {

    public KeywordParser(Pointer pointer) {
        super(pointer);
    }

    public KeywordParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Keyword parse() {
        return new Keyword(this.pointer.getWord(), this.pointer.getLastSeparator(), this.pointer.getSeparator());
    }
}
