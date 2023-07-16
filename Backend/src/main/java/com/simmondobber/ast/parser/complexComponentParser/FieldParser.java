package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.NameParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class FieldParser extends AstParser {

    public FieldParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Field parse() {
        Preamble preamble = new PreambleParser(this.pointer).parse();
        Type type = new TypeParser(this.pointer).parse();
        Name name = new NameParser(this.pointer).parse();
        ValueAssignment valueAssignment = null;
        if (this.pointer.lookupCharacter() == '=') {
            valueAssignment = new ValueAssignmentParser(this.pointer).parse();
        }
        Character semicolon = new CharacterParser(this.pointer).parse();
        return new Field(preamble, type, name, valueAssignment, semicolon);
    }
}
