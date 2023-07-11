package com.simmondobber.ast.components.complexAstComponents.annotation;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Annotation extends ComplexAstComponent {

    private final Character at;
    private final Name name;
    private final Args args;

    public Annotation(List<Interjection> interjections, Name name, Args args) {
        super(interjections);
        this.at = new Character("@");
        this.name = name;
        this.args = args;
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        return List.of(this.at, this.name, this.args);
    }
}
