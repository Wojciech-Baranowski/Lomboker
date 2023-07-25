package com.simmondobber.lomboker.lombokize.boilerplateCleaner;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.comparator.AstComparator;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.MethodFactory;

import java.util.List;

public class BoilerplateCleaner {

    private final AstComponentFilter astComponentFilter;
    private final MethodFactory methodFactory;
    private final AstComparator astComparator;

    public BoilerplateCleaner() {
        this.astComponentFilter = new AstComponentFilter();
        this.methodFactory = new MethodFactory();
        this.astComparator = new AstComparator();
    }

    public void removeRedundantMethods(Ast ast) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot())
                .forEach(this::removeRedundantMethods);
    }

    private void removeRedundantMethods(Class clazz) {
        List<Field> classFields = this.astComponentFilter.getFieldListFromAstComponent(clazz);
        List<Method> classMethod = this.astComponentFilter.getMethodListFromAstComponent(clazz);
        List<Method> methodsBasedOnClassFields = this.methodFactory.generateMethodsBasedOnClassFields(classFields);
        List<Method> methodsToRemove = filterMethodsToRemove(classMethod, methodsBasedOnClassFields);
        clazz.getClassBody().getClassContent().getClassContentComponents().removeAll(methodsToRemove);
    }

    private List<Method> filterMethodsToRemove(List<Method> classMethods, List<Method> methodsBasedOnClassFields) {
        return classMethods.stream()
                .filter(method -> isMethodGenerated(methodsBasedOnClassFields, method))
                .toList();
    }

    private boolean isMethodGenerated(List<Method> methodsBasedOnFields, Method method) {
        return methodsBasedOnFields.stream()
                .anyMatch(methodBasedOnField -> this.astComparator.areMethodsEqual(methodBasedOnField.getSyntax(), method.getSyntax()));
    }
}
