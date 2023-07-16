package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class CharacterParser extends AstParser {

    public CharacterParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Character parse() {
        return new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
    }
}
