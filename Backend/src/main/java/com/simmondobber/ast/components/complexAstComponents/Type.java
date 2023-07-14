package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Type extends ComplexAstComponent {

    private final Name name;
    private final Generic generic;

    public Type(Name name, Generic generic) {
        this.name = name;
        this.generic = generic;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.generic == null) {
            return List.of(this.name);
        }
        return List.of(this.name, this.generic);
    }
}
