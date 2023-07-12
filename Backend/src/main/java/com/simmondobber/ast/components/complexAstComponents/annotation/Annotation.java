package com.simmondobber.ast.components.complexAstComponents.annotation;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Annotation extends ComplexAstComponent {

    private final Character at;
    private final Name name;
    private final Args args;
    private final Separator separator;

    public Annotation(Name name, Args args, Separator separator) {
        this.at = new Character("@");
        this.name = name;
        this.args = args;
        this.separator = separator;
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        return List.of(this.at, this.name, this.args, this.separator);
    }
}
