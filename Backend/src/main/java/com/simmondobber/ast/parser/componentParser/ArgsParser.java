package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.parser.utils.Pointer;

public class ArgsParser extends AstParser {

    public ArgsParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Args parse() {
        Character leftBracket = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Listing listing = null;
        if (this.pointer.lookupCharacter() != ')') {
            listing = new Listing(this.pointer.getUntil(')'));
        }
        Character rightBracket = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Args(leftBracket, listing, rightBracket);
    }
}
