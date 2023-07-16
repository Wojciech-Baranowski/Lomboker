package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.ThrowsListing;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ThrowsListingParser extends AstParser {

    public ThrowsListingParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ThrowsListing parse() {
        return new ThrowsListing(this.pointer.getUntil("{;"));
    }
}
