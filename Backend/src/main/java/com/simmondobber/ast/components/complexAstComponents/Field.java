package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Field extends ComplexAstComponent implements ClassContentComponent {

    private Preamble preamble;
    private Type type;
    private Name name;
    private ValueAssignment valueAssignment;
    private Character semicolon;

    public Field(Preamble preamble, Type type, Name name, ValueAssignment valueAssignment, Character semicolon) {
        this.preamble = preamble;
        this.type = type;
        this.name = name;
        this.valueAssignment = valueAssignment;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.valueAssignment == null) {
            return List.of(this.preamble, this.type, this.name, this.semicolon);
        } else {
            return List.of(this.preamble, this.type, this.name, this.valueAssignment, this.semicolon);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.preamble.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.preamble.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        return this.semicolon.getBackSeparator();
    }

    @Override
    public void setBackSeparator(String separator) {
        this.semicolon.setBackSeparator(separator);
    }
}
