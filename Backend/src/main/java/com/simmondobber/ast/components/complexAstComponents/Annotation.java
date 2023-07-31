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
public class Annotation extends ComplexAstComponent implements PreambleComponent {

    private Character at;
    private Name name;
    private Args args;

    public Annotation(Character at, Name name, Args args) {
        this.at = at;
        this.name = name;
        this.args = args;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.args == null) {
            return List.of(this.at, this.name);
        } else {
            return List.of(this.at, this.name, this.args);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.at.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        if (this.args == null) {
            return this.name.getBackSeparator();
        } else {
            return this.args.getBackSeparator();
        }
    }
}
