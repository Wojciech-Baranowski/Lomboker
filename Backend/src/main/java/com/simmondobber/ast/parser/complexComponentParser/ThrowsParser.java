package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Throws;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.ThrowsListing;
import com.simmondobber.ast.parser.simpleComponentParser.KeywordParser;
import com.simmondobber.ast.parser.simpleComponentParser.ThrowsListingParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ThrowsParser extends AstParser {

    public ThrowsParser(Pointer pointer) {
        super(pointer);
    }

    public ThrowsParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Throws parse() {
        Keyword throwsKeyword = new KeywordParser(this.pointer).parse();
        ThrowsListing throwsListing = new ThrowsListingParser(this.pointer).parse();
        return new Throws(throwsKeyword, throwsListing);
    }
}
