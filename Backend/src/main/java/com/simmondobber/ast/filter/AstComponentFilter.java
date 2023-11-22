package com.simmondobber.ast.filter;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;

import java.util.ArrayList;
import java.util.List;

public class AstComponentFilter {

    public List<Class> extractClassesFromGivenAstComponent(ComplexAstComponent astComponent, boolean recursive) {
        List<Class> classes = new ArrayList<>();
        for (AstComponent component : astComponent.getChildAstComponents()) {
            if (component instanceof Class) {
                classes.add((Class) component);
            }
            if (component instanceof ComplexAstComponent && (!(component instanceof Class) || recursive)) {
                classes.addAll(extractClassesFromGivenAstComponent((ComplexAstComponent) component, recursive));
            }
        }
        return classes;
    }

    public List<Method> extractMethodsFromGivenAstComponent(ComplexAstComponent astComponent, boolean recursive) {
        List<Method> methods = new ArrayList<>();
        for (AstComponent component : astComponent.getChildAstComponents()) {
            if (component instanceof Method) {
                methods.add((Method) component);
            }
            if (component instanceof ComplexAstComponent && (!(component instanceof Method) || recursive)) {
                methods.addAll(extractMethodsFromGivenAstComponent((ComplexAstComponent) component, recursive));
            }
        }
        return methods;
    }

    public List<Field> extractFieldsFromGivenAstComponent(ComplexAstComponent astComponent, boolean recursive) {
        List<Field> fields = new ArrayList<>();
        for (AstComponent component : astComponent.getChildAstComponents()) {
            if (component instanceof Field) {
                fields.add((Field) component);
            }
            if (component instanceof ComplexAstComponent && (!(component instanceof Field) || recursive)) {
                fields.addAll(extractFieldsFromGivenAstComponent((ComplexAstComponent) component, recursive));
            }
        }
        return fields;
    }
}
