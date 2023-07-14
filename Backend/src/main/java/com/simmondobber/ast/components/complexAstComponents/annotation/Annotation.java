package com.simmondobber.ast.components.complexAstComponents.annotation;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.complexAstComponents.preamble.PreambleComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Annotation extends ComplexAstComponent implements PreambleComponent {

    private final Character at;
    private final Name name;
    private final Args args;

    public Annotation(Character at, Name name, Args args) {
        this.at = at;
        this.name = name;
        this.args = args;
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        if (this.args == null) {
            return List.of(this.at, this.name);
        } else {
            return List.of(this.at, this.name, this.args);
        }
    }
}
