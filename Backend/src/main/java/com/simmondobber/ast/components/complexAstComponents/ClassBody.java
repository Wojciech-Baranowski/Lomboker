package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;

import java.util.List;

public class ClassBody extends ComplexAstComponent {

    private final Character leftCurly;
    private final EnumValues enumValues;
    private final ClassContent classContent;
    private final Character rightCurly;

    public ClassBody(Character leftCurly, EnumValues enumValues, ClassContent classContent, Character rightCurly) {
        this.leftCurly = leftCurly;
        this.enumValues = enumValues;
        this.classContent = classContent;
        this.rightCurly = rightCurly;
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        if (this.enumValues == null) {
            return List.of(this.leftCurly, this.classContent, this.rightCurly);
        } else {
            return List.of(this.leftCurly, this.enumValues, this.classContent, this.rightCurly);
        }
    }
}
