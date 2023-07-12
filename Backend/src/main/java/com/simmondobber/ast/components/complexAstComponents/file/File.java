package com.simmondobber.ast.components.complexAstComponents.file;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.class_.Class;
import com.simmondobber.ast.components.complexAstComponents.import_.Import;
import com.simmondobber.ast.components.complexAstComponents.package_.Package;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.ArrayList;
import java.util.List;

public class File extends ComplexAstComponent {

    private final Package aPackage;
    private final List<Import> imports;
    private final List<Class> classes;
    private final Separator separator;

    public File(Package aPackage, List<Import> imports, List<Class> classes, Separator separator) {
        this.aPackage = aPackage;
        this.imports = imports;
        this.classes = classes;
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> childComponents = new ArrayList<>();
        childComponents.add(this.aPackage);
        childComponents.addAll(this.imports);
        childComponents.addAll(this.classes);
        childComponents.add(this.separator);
        return childComponents;
    }
}
