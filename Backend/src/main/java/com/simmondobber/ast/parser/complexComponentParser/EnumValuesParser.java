package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.EnumValuesListing;
import com.simmondobber.ast.parser.simpleComponentParser.EnumValuesListingParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class EnumValuesParser extends AstParser {

    public EnumValuesParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public EnumValues parse() {
        EnumValuesListing enumValuesListing = new EnumValuesListingParser(this.pointer).parse();
        Character semicolon = null;
        if (this.pointer.lookupCharacter() == ';') {
            semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        }
        return new EnumValues(enumValuesListing, semicolon);
    }
}
