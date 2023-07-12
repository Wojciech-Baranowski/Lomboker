package com.simmondobber.ast.components.complexAstComponents.class_.classContent;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.class_.Class;
import com.simmondobber.ast.components.complexAstComponents.field.Field;
import com.simmondobber.ast.components.complexAstComponents.method.Method;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.ArrayList;
import java.util.List;

public class ClassContent extends ComplexAstComponent {

    private final List<ClassContentComponent> classContentComponents;
    private final Separator separator;

    public ClassContent(List<ClassContentComponent> classContentComponents, Separator separator) {
        this.classContentComponents = classContentComponents;
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> components = new ArrayList<>(this.classContentComponents);
        components.add(this.separator);
        return components;
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
