package com.simmondobber.ast.parser.generic;

import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;

public class GenericParser extends AstParser {

    public GenericParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Generic parse() {
        Character leftAngle = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Extension extension = new Extension(this.pointer.getInside('>'));
        Character rightAngle = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Generic(leftAngle, extension, rightAngle);
    }
}
