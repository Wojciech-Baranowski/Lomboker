package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.ArrayBrackets;
import com.simmondobber.ast.components.simpleAstComponents.ArraySize;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.simpleComponentParser.ArraySizeParser;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ArrayBracketsParser extends AstParser {

    public ArrayBracketsParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ArrayBrackets parse() {
        Character leftBracket = new CharacterParser(this.pointer).parse();
        ArraySize arraySize = null;
        if (this.pointer.lookupCharacter() != ']') {
            arraySize = new ArraySizeParser(this.pointer).parse();
        }
        Character rightBracket = new CharacterParser(this.pointer).parse();
        return new ArrayBrackets(leftBracket, arraySize, rightBracket);
    }
}
