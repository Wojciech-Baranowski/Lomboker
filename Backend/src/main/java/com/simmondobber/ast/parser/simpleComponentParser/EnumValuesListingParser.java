package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.EnumValuesListing;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class EnumValuesListingParser extends AstParser {

    public EnumValuesListingParser(Pointer pointer) {
        super(pointer);
    }

    public EnumValuesListingParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public EnumValuesListing parse() {
        return new EnumValuesListing(this.pointer.getUntil(";}"));
    }
}
