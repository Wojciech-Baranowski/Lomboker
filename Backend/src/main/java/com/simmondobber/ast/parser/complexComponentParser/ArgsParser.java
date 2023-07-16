package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.simpleAstComponents.ArgsListing;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.simpleComponentParser.ArgsListingParser;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ArgsParser extends AstParser {

    public ArgsParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Args parse() {
        Character leftBracket = new CharacterParser(this.pointer).parse();
        ArgsListing argsListing = null;
        if (this.pointer.lookupCharacter() != ')') {
            argsListing = new ArgsListingParser(this.pointer).parse();
        }
        Character rightBracket = new CharacterParser(this.pointer).parse();
        return new Args(leftBracket, argsListing, rightBracket);
    }
}
