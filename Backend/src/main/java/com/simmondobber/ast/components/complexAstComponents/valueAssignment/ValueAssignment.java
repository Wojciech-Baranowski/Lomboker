package com.simmondobber.ast.components.complexAstComponents.valueAssignment;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Separator;
import com.simmondobber.ast.components.simpleAstComponents.Value;

import java.util.List;

public class ValueAssignment extends ComplexAstComponent {

    private final Character equals;
    private final Value value;
    private final Separator separator;

    public ValueAssignment(Value value, Separator separator) {
        this.equals = new Character("=");
        this.value = value;
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.equals, this.value, this.separator);
    }
}
