package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClassBody extends ComplexAstComponent {

    private Character leftCurly;
    private EnumValues enumValues;
    private ClassContent classContent;
    private Character rightCurly;

    public ClassBody(Character leftCurly, EnumValues enumValues, ClassContent classContent, Character rightCurly) {
        this.leftCurly = leftCurly;
        this.enumValues = enumValues;
        this.classContent = classContent;
        this.rightCurly = rightCurly;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.classContent == null && this.enumValues == null) {
            return List.of(this.leftCurly, this.rightCurly);
        } else if (this.classContent == null) {
            return List.of(this.leftCurly, this.enumValues, this.rightCurly);
        } else if (this.enumValues == null) {
            return List.of(this.leftCurly, this.classContent, this.rightCurly);
        } else {
            return List.of(this.leftCurly, this.enumValues, this.classContent, this.rightCurly);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.leftCurly.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        return this.rightCurly.getBackSeparator();
    }
}
