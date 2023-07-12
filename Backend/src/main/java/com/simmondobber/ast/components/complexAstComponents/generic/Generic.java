package com.simmondobber.ast.components.complexAstComponents.generic;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Generic extends ComplexAstComponent {

    private final Character leftAngle;
    private final Name name;
    private final Extension extension;
    private final Character rightAngle;
    private final Separator separator;

    public Generic(Name name, Extension extension, Separator separator) {
        this.leftAngle = new Character("<");
        this.name = name;
        this.extension = extension;
        this.rightAngle = new Character(">");
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftAngle, this.name, this.extension, this.rightAngle, this.separator);
    }
}
