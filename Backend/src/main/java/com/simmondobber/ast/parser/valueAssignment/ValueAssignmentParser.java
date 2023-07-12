package com.simmondobber.ast.parser.valueAssignment;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.valueAssignment.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Separator;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;

public class ValueAssignmentParser extends AstParser {

    public ValueAssignmentParser(String stringToParse, Pointer pointer) {
        super(stringToParse, pointer);
    }

    @Override
    public AstComponent parse() {
        omitCharacter();
        Value value = new Value(getUntilCharacterAndMove(';'));
        Separator separator = getNextSeparatorAndMove();
        return new ValueAssignment(value, separator);
    }
}
