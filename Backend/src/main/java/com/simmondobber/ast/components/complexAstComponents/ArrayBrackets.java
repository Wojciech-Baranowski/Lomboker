package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;

import java.util.List;

public class ArrayBrackets extends ComplexAstComponent {

    private final Character leftBracket;
    private final Value value;
    private final Character rightBracket;

    public ArrayBrackets(Character leftBracket, Value value, Character rightBracket) {
        this.leftBracket = leftBracket;
        this.value = value;
        this.rightBracket = rightBracket;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.value == null) {
            return List.of(this.leftBracket, this.rightBracket);
        } else {
            return List.of(this.leftBracket, this.value, this.rightBracket);
        }
    }
}
