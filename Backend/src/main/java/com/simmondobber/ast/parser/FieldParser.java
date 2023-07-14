package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;

public class FieldParser extends AstParser {

    public FieldParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Field parse() {
        Preamble preamble = new PreambleParser(this.pointer).parse();
        Type type = new TypeParser(this.pointer).parse();
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        ValueAssignment valueAssignment = null;
        if (this.pointer.lookupCharacter() == '=') {
            valueAssignment = new ValueAssignmentParser(this.pointer).parse();
        }
        Character semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Field(preamble, type, name, valueAssignment, semicolon);
    }
}
