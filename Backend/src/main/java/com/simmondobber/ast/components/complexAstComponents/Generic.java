package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.GenericExtension;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Generic extends ComplexAstComponent implements PreambleComponent {

    private Character leftAngle;
    private GenericExtension genericExtension;
    private Character rightAngle;

    public Generic(Character leftAngle, GenericExtension genericExtension, Character rightAngle) {
        this.leftAngle = leftAngle;
        this.genericExtension = genericExtension;
        this.rightAngle = rightAngle;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftAngle, this.genericExtension, this.rightAngle);
    }

    @Override
    public String getFrontSeparator() {
        return this.leftAngle.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.leftAngle.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        return this.rightAngle.getBackSeparator();
    }

    @Override
    public void setBackSeparator(String separator) {
        this.rightAngle.setBackSeparator(separator);
    }
}
