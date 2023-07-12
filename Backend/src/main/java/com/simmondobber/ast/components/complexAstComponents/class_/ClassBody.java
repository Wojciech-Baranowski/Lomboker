package com.simmondobber.ast.components.complexAstComponents.class_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.class_.classContent.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.enumValues.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class ClassBody extends ComplexAstComponent {

    private final Character leftCurly;
    private final EnumValues enumValues;
    private final ClassContent classContent;
    private final Character rightCurly;
    private final Separator separator;

    public ClassBody(EnumValues enumValues, ClassContent classContent, Separator separator) {
        this.leftCurly = new Character("{");
        this.enumValues = enumValues;
        this.classContent = classContent;
        this.rightCurly = new Character("}");
        this.separator = separator;
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        return List.of(this.leftCurly, this.enumValues, this.classContent, this.rightCurly, this.separator);
    }
}
