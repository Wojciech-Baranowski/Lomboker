package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.GenericExtension;

import java.util.List;

public class Generic extends ComplexAstComponent implements PreambleComponent {

    private final Character leftAngle;
    private final GenericExtension genericExtension;
    private final Character rightAngle;

    public Generic(Character leftAngle, GenericExtension genericExtension, Character rightAngle) {
        this.leftAngle = leftAngle;
        this.genericExtension = genericExtension;
        this.rightAngle = rightAngle;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftAngle, this.genericExtension, this.rightAngle);
    }
}
