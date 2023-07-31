package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class File extends ComplexAstComponent {

    private Package package_;
    private List<Import> imports;
    private List<Class> classes;

    public File(Package package_, List<Import> imports, List<Class> classes) {
        this.package_ = package_;
        this.imports = imports;
        this.classes = classes;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> childComponents = new ArrayList<>();
        childComponents.add(this.package_);
        childComponents.addAll(this.imports);
        childComponents.addAll(this.classes);
        return childComponents;
    }

    @Override
    public String getFrontSeparator() {
        return this.package_.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        return this.classes.get(this.classes.size() - 1).getBackSeparator();
    }
}
