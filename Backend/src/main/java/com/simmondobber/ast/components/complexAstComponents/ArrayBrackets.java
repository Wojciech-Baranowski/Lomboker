package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.ArraySize;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArrayBrackets extends ComplexAstComponent {

    private Character leftBracket;
    private ArraySize arraySize;
    private Character rightBracket;

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

    @Override
    public String getFrontSeparator() {
        return this.leftBracket.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        return this.rightBracket.getBackSeparator();
    }
}
