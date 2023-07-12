package com.simmondobber.ast.parser.args;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.components.simpleAstComponents.Separator;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;

public class ArgsParser extends AstParser {

    public ArgsParser(String stringToParse, Pointer pointer) {
        super(stringToParse, pointer);
    }

    @Override
    public AstComponent parse() {
        omitCharacter();
        Listing listing = new Listing(getUntilCharacterAndMove(')'));
        omitCharacter();
        Separator separator = getNextSeparatorAndMove();
        return new Args(listing, separator);
    }
}
