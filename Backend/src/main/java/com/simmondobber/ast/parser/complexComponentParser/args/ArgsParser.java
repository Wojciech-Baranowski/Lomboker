package com.simmondobber.ast.parser.complexComponentParser.args;

import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;

public class ArgsParser extends AstParser {

    public ArgsParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Args parse() {
        Character leftBracket = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Listing listing = new Listing(this.pointer.getInside(')'));
        Character rightBracket = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Args(leftBracket, listing, rightBracket);
    }
}
