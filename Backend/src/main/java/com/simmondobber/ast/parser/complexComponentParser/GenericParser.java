package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.GenericExtension;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.GenericExtensionParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class GenericParser extends AstParser {

    public GenericParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Generic parse() {
        Character leftAngle = new CharacterParser(this.pointer).parse();
        GenericExtension genericExtension = new GenericExtensionParser(this.pointer).parse();
        Character rightAngle = new CharacterParser(this.pointer).parse();
        return new Generic(leftAngle, genericExtension, rightAngle);
    }
}
