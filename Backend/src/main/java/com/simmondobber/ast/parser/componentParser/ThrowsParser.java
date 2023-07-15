package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.Throws;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.parser.utils.Pointer;

public class ThrowsParser extends AstParser {

    public ThrowsParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Throws parse() {
        Keyword throwsKeyword = new Keyword(this.pointer.getWord(), this.pointer.getSeparator());
        Listing listing = new Listing(this.pointer.getUntil("{;"));
        return new Throws(throwsKeyword, listing);
    }
}
