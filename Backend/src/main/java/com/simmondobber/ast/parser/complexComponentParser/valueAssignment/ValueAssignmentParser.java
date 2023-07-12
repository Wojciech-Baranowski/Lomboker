package com.simmondobber.ast.parser.complexComponentParser.valueAssignment;

import com.simmondobber.ast.components.complexAstComponents.valueAssignment.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;

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
