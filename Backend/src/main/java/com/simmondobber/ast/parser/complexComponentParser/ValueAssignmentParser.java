package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.ValueParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ValueAssignmentParser extends AstParser {

    public ValueAssignmentParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ValueAssignment parse() {
        Character equals = new CharacterParser(this.pointer).parse();
        Value value = new ValueParser(this.pointer).parse();
        return new ValueAssignment(equals, value);
    }
}
