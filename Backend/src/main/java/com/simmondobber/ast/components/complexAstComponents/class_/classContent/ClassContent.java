package com.simmondobber.ast.components.complexAstComponents.class_.classContent;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.class_.Class;
import com.simmondobber.ast.components.complexAstComponents.field.Field;
import com.simmondobber.ast.components.complexAstComponents.method.Method;

import java.util.ArrayList;
import java.util.List;

public class ClassContent extends ComplexAstComponent {

    private final List<ClassContentComponent> classContentComponents;

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
}
