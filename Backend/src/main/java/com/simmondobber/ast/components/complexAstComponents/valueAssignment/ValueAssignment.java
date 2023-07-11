package com.simmondobber.ast.components.complexAstComponents.valueAssignment;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import com.simmondobber.ast.components.simpleAstComponents.Value;

import java.util.List;

public class ValueAssignment extends ComplexAstComponent {

    private final Character equals;
    private final Value value;

    public ValueAssignment(List<Interjection> interjections, Value value) {
        super(interjections);
        this.equals = new Character("=");
        this.value = value;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.equals, this.value);
    }
}
