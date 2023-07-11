package com.simmondobber.ast.components.complexAstComponents.generic;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Generic extends ComplexAstComponent {

    private final Character leftAngle;
    private final Name name;
    private final Extension extension;
    private final Character rightAngle;

    public Generic(List<Interjection> interjections, Name name, Extension extension) {
        super(interjections);
        this.leftAngle = new Character("<");
        this.name = name;
        this.extension = extension;
        this.rightAngle = new Character(">");
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftAngle, this.name, this.extension, this.rightAngle);
    }
}
