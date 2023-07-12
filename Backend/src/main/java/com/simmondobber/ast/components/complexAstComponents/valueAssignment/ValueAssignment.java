package com.simmondobber.ast.components.complexAstComponents.valueAssignment;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;

import java.util.List;

public class ValueAssignment extends ComplexAstComponent {

    private final Character equals;
    private final Value value;

    public ValueAssignment(Character equals, Value value) {
        this.equals = equals;
        this.value = value;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.equals, this.value);
    }
}
