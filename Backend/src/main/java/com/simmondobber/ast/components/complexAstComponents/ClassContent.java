package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClassContent extends ComplexAstComponent {

    private List<ClassContentComponent> classContentComponents;

    public ClassContent(List<ClassContentComponent> classContentComponents) {
        this.classContentComponents = classContentComponents;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return new ArrayList<>(this.classContentComponents);
    }

    public List<Field> getFields() {
        return this.classContentComponents.stream()
                .filter(component -> component instanceof Field)
                .map(component -> (Field) component)
                .toList();
    }

    public List<Method> getMethods() {
        return this.classContentComponents.stream()
                .filter(component -> component instanceof Method)
                .map(component -> (Method) component)
                .toList();
    }

    public List<Class> getClasses() {
        return this.classContentComponents.stream()
                .filter(component -> component instanceof Class)
                .map(component -> (Class) component)
                .toList();
    }

    @Override
    public String getFrontSeparator() {
        if (this.classContentComponents.isEmpty()) {
            return "";
        }
        return this.classContentComponents.get(0).getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        if (this.classContentComponents.isEmpty()) {
            return "";
        }
        return this.classContentComponents.get(this.classContentComponents.size() - 1).getBackSeparator();
    }
}
