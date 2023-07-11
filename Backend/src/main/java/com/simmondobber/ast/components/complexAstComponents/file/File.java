package com.simmondobber.ast.components.complexAstComponents.file;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.class_.Class;
import com.simmondobber.ast.components.complexAstComponents.import_.Import;
import com.simmondobber.ast.components.complexAstComponents.package_.Package;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;

import java.util.ArrayList;
import java.util.List;

public class File extends ComplexAstComponent {

    private final Package aPackage;
    private final List<Import> imports;
    private final List<Class> classes;

    public File(List<Interjection> interjections, Package aPackage, List<Import> imports, List<Class> classes) {
        super(interjections);
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
