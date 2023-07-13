package com.simmondobber.ast.components.complexAstComponents.field;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.field.preamble.FieldPreamble;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.complexAstComponents.valueAssignment.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Field extends ComplexAstComponent {

    private final FieldPreamble fieldPreamble;
    private final Type type;
    private final Name name;
    private final ValueAssignment valueAssignment;
    private final Character semicolon;

    public Field(FieldPreamble fieldPreamble, Type type, Name name, ValueAssignment valueAssignment, Character semicolon) {
        this.fieldPreamble = fieldPreamble;
        this.type = type;
        this.name = name;
        this.valueAssignment = valueAssignment;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.valueAssignment == null) {
            return List.of(this.fieldPreamble, this.type, this.name, this.semicolon);
        } else {
            return List.of(this.fieldPreamble, this.type, this.name, this.valueAssignment, this.semicolon);
        }
    }
}
