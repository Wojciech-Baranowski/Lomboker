package com.simmondobber.lomboker.lombokize.boilerplateCleaner;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.lomboker.lombokize.AstComponentFilter;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.MethodFactory;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.List;

public class BoilerplateCleaner {

    private final AstComponentFilter astComponentFilter;
    private final MethodFactory methodFactory;

    public BoilerplateCleaner() {
        this.astComponentFilter = new AstComponentFilter();
        this.methodFactory = new MethodFactory();
    }

    public void removeRedundantMethods(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot())
                .forEach(clazz -> removeRedundantMethods(clazz, annotationsConfig));
    }

    private void removeRedundantMethods(Class clazz, AnnotationsConfig annotationsConfig) {
        List<Field> classFields = this.astComponentFilter.getFieldListFromAstComponent(clazz);
        List<Method> methodsBasedOnClassFields = this.methodFactory.generateMethodsBasedOnClassFields(classFields, annotationsConfig);
        this.astComponentFilter.getMethodListFromAstComponent(clazz)
                .removeIf(method -> isMethodGenerated(methodsBasedOnClassFields, method));
    }

    private boolean isMethodGenerated(List<Method> methodsBasedOnFields, Method method) {
        return methodsBasedOnFields.stream()
                .anyMatch(methodBasedOnField -> areMethodsEqual(methodBasedOnField.getSyntax(), method.getSyntax()));
    }

    private boolean areMethodsEqual(String methodSyntax1, String methodSyntax2) {
        methodSyntax1 = methodSyntax1.replaceAll("`.*?`", " ");
        methodSyntax2 = methodSyntax2.replaceAll("`.*?`", " ");
        methodSyntax1 = methodSyntax1.replaceAll("\\s+", " ");
        methodSyntax2 = methodSyntax2.replaceAll("\\s+", " ");
        return methodSyntax1.equals(methodSyntax2);
    }
}
