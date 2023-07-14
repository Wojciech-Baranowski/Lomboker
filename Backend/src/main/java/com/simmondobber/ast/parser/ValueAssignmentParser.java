package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.utils.Pointer;

public class ValueAssignmentParser extends AstParser {

    public ValueAssignmentParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ValueAssignment parse() {
        Character equals = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Value value = new Value(this.pointer.getUntil(';'));
        return new ValueAssignment(equals, value);
    }
}
