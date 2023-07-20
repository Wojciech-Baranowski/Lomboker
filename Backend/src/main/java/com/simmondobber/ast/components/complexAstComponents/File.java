package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class File extends ComplexAstComponent {

    private final Package aPackage;
    private final List<Import> imports;
    private final List<Class> classes;

    public File(Package aPackage, List<Import> imports, List<Class> classes) {
        this.aPackage = aPackage;
        this.imports = imports;
        this.classes = classes;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> childComponents = new ArrayList<>();
        childComponents.add(this.aPackage);
        childComponents.addAll(this.imports);
        childComponents.addAll(this.classes);
        return childComponents;
    }
}
