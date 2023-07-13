package com.simmondobber.ast.parser.field;

import com.simmondobber.ast.components.complexAstComponents.field.Field;
import com.simmondobber.ast.components.complexAstComponents.field.preamble.FieldPreamble;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.complexAstComponents.valueAssignment.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.type.TypeParser;
import com.simmondobber.ast.parser.valueAssignment.ValueAssignmentParser;

public class FieldParser extends AstParser {

    public FieldParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Field parse() {
        FieldPreamble fieldPreamble = new FieldPreambleParser(this.pointer).parse();
        Type type = new TypeParser(this.pointer).parse();
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        ValueAssignment valueAssignment = null;
        if (this.pointer.lookupCharacter() == '=') {
            valueAssignment = new ValueAssignmentParser(this.pointer).parse();
        }
        Character semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Field(fieldPreamble, type, name, valueAssignment, semicolon);
    }
}
