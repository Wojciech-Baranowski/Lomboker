package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Path;

import java.util.List;

public class Type extends ComplexAstComponent {

    private final Path path;
    private final Generic generic;

    public Type(Path path, Generic generic) {
        this.path = path;
        this.generic = generic;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.generic == null) {
            return List.of(this.path);
        }
        return List.of(this.path, this.generic);
    }
}
