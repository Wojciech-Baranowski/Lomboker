package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.parser.utils.Pointer;

public class EnumValuesParser extends AstParser {

    public EnumValuesParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public EnumValues parse() {
        Listing listing = new Listing(this.pointer.getUntil(";}"));
        Character semicolon = null;
        if (this.pointer.lookupCharacter() == ';') {
            semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        }
        return new EnumValues(listing, semicolon);
    }
}
