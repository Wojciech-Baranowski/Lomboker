package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Type extends ComplexAstComponent {

    private final Path path;
    private final Generic generic;
    private final List<ArrayBrackets> arrayBrackets;

    public Type(Path path, Generic generic, List<ArrayBrackets> arrayBrackets) {
        this.path = path;
        this.generic = generic;
        this.arrayBrackets = arrayBrackets;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> components = new ArrayList<>();
        components.add(this.path);
        if (this.generic != null) {
            components.add(this.generic);
        }
        if (this.arrayBrackets != null) {
            components.addAll(this.arrayBrackets);
        }
        return components;
    }

    @Override
    public String getFrontSeparator() {
        return this.path.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        if (this.arrayBrackets.isEmpty()) {
            if (this.generic == null) {
                return this.path.getBackSeparator();
            } else {
                return this.generic.getBackSeparator();
            }
        } else {
            return this.arrayBrackets.get(this.arrayBrackets.size() - 1).getBackSeparator();
        }
    }
}
