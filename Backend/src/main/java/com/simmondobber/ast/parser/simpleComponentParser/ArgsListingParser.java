package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.ArgsListing;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ArgsListingParser extends AstParser {

    public ArgsListingParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ArgsListing parse() {
        return new ArgsListing(this.pointer.getUntil(')'));
    }
}
