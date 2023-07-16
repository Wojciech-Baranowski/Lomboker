package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.ArraySize;
import com.simmondobber.ast.components.simpleAstComponents.Character;

import java.util.List;

public class ArrayBrackets extends ComplexAstComponent {

    private final Character leftBracket;
    private final ArraySize arraySize;
    private final Character rightBracket;

    public ArrayBrackets(Character leftBracket, ArraySize arraySize, Character rightBracket) {
        this.leftBracket = leftBracket;
        this.arraySize = arraySize;
        this.rightBracket = rightBracket;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.arraySize == null) {
            return List.of(this.leftBracket, this.rightBracket);
        } else {
            return List.of(this.leftBracket, this.arraySize, this.rightBracket);
        }
    }
}
