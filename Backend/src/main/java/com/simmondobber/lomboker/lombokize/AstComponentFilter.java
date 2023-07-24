package com.simmondobber.lomboker.lombokize;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;

import java.util.ArrayList;
import java.util.List;

public class AstComponentFilter {

    public List<Class> getClassListFromAstComponent(ComplexAstComponent astComponent) {
        List<Class> classes = new ArrayList<>();
        for (AstComponent component : astComponent.getChildAstComponents()) {
            if (component instanceof Class) {
                classes.add((Class) component);
            }
            if (component instanceof ComplexAstComponent) {
                classes.addAll(getClassListFromAstComponent((ComplexAstComponent) component));
            }
        }
        return classes;
    }

    public List<Method> getMethodListFromAstComponent(ComplexAstComponent astComponent) {
        List<Method> methods = new ArrayList<>();
        for (AstComponent component : astComponent.getChildAstComponents()) {
            if (component instanceof Method) {
                methods.add((Method) component);
            }
            if (component instanceof ComplexAstComponent) {
                methods.addAll(getMethodListFromAstComponent((ComplexAstComponent) component));
            }
        }
        return methods;
    }

    public List<Field> getFieldListFromAstComponent(ComplexAstComponent astComponent) {
        List<Field> fields = new ArrayList<>();
        for (AstComponent component : astComponent.getChildAstComponents()) {
            if (component instanceof Field) {
                fields.add((Field) component);
            }
            if (component instanceof ComplexAstComponent) {
                fields.addAll(getFieldListFromAstComponent((ComplexAstComponent) component));
            }
        }
        return fields;
    }
}
