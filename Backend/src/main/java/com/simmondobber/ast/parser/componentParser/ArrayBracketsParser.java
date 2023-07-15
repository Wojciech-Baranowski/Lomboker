package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.ArrayBrackets;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.utils.Pointer;

public class ArrayBracketsParser extends AstParser {

    public ArrayBracketsParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ArrayBrackets parse() {
        Character leftBracket = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Value value = null;
        if (this.pointer.lookupCharacter() != ']') {
            value = new Value(this.pointer.getUntil(']'));
        }
        Character rightBracket = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new ArrayBrackets(leftBracket, value, rightBracket);
    }
}
