package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValueAssignment extends ComplexAstComponent {

    private Character equals;
    private Value value;

    public ValueAssignment(Character equals, Value value) {
        this.equals = equals;
        this.value = value;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.equals, this.value);
    }

    @Override
    public String getFrontSeparator() {
        return this.equals.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.equals.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        return this.value.getBackSeparator();
    }

    @Override
    public void setBackSeparator(String separator) {
        this.value.setBackSeparator(separator);
    }
}
