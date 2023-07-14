package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.parser.utils.Pointer;

public class GenericParser extends AstParser {

    public GenericParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Generic parse() {
        Character leftAngle = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Extension extension = new Extension(this.pointer.getUntil('>'));
        Character rightAngle = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Generic(leftAngle, extension, rightAngle);
    }
}
