package com.simmondobber.ast.components.complexAstComponents.field;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.field.preamble.FieldPreamble;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.complexAstComponents.valueAssignment.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Field extends ComplexAstComponent {

    private final FieldPreamble fieldPreamble;
    private final Type type;
    private final Name name;
    private final ValueAssignment valueAssignment;
    private final Character semicolon;
    private final Separator separator;

    public Field(FieldPreamble fieldPreamble, Type type, Name name, ValueAssignment valueAssignment, Separator separator) {
        this.fieldPreamble = fieldPreamble;
        this.type = type;
        this.name = name;
        this.valueAssignment = valueAssignment;
        this.semicolon = new Character(";");
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.fieldPreamble, this.type, this.name, this.valueAssignment, this.semicolon, this.separator);
    }
}
