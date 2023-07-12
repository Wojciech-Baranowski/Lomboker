package com.simmondobber.ast.components.complexAstComponents.generic;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Extension;

import java.util.List;

public class Generic extends ComplexAstComponent {

    private final Character leftAngle;
    private final Extension extension;
    private final Character rightAngle;

    public Generic(Character leftAngle, Extension extension, Character rightAngle) {
        this.leftAngle = leftAngle;
        this.extension = extension;
        this.rightAngle = rightAngle;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftAngle, this.extension, this.rightAngle);
    }
}
