package com.simmondobber.lomboker.lombokize.boilerplateCleaner;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.lomboker.common.Trimmer;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.MethodFactory;

import java.util.List;

public class BoilerplateCleaner {

    private final AstComponentFilter astComponentFilter;
    private final MethodFactory methodFactory;

    public BoilerplateCleaner() {
        this.astComponentFilter = new AstComponentFilter();
        this.methodFactory = new MethodFactory();
    }

    public void removeDefaultMethodsFromAst(Ast ast, boolean actOnInnerClasses, boolean ignoreMethodAnnotations) {
        List<Class> astClasses = this.astComponentFilter.extractClassesFromGivenAstComponent(ast.getAstRoot(), actOnInnerClasses);
        astClasses.forEach(clazz -> removeDefaultMethodsFromClass(clazz, actOnInnerClasses, ignoreMethodAnnotations));
    }

    private void removeDefaultMethodsFromClass(Class clazz, boolean actOnInnerClasses, boolean ignoreMethodAnnotations) {
        List<Method> methodsToRemove = getClassMethodsToRemove(clazz, actOnInnerClasses, ignoreMethodAnnotations);
        methodsToRemove.forEach(method -> removeMethod(method, clazz.getClassBody().getClassContent()));
    }

    private List<Method> getClassMethodsToRemove(Class clazz, boolean actOnInnerClasses, boolean ignoreMethodAnnotations) {
        List<Method> defaultMethodsBasedOnClassFields = getDefaultMethodsBasedOnClassFields(clazz, actOnInnerClasses);
        return this.astComponentFilter.extractMethodsFromGivenAstComponent(clazz, actOnInnerClasses).stream()
                .filter(methodToCheck -> isMethodDefault(methodToCheck, defaultMethodsBasedOnClassFields, ignoreMethodAnnotations))
                .toList();
    }

    private List<Method> getDefaultMethodsBasedOnClassFields(Class clazz, boolean actOnInnerClasses) {
        List<Field> classFields = this.astComponentFilter.extractFieldsFromGivenAstComponent(clazz, actOnInnerClasses);
        return this.methodFactory.generateMethodsBasedOnClassFields(classFields);
    }

    private boolean isMethodDefault(Method methodToCheck, List<Method> defaultMethodsBasedOnFields, boolean ignoreMethodAnnotations) {
        if (ignoreMethodAnnotations) {
            methodToCheck.getPreamble().getPreambleComponents().removeAll(methodToCheck.getPreamble().getAnnotations());
        }
        return defaultMethodsBasedOnFields.stream()
                .anyMatch(methodBasedOnField -> areMethodsSyntacticallyEqual(methodBasedOnField.getFullSyntax(), methodToCheck.getFullSyntax()));
    }

    private boolean areMethodsSyntacticallyEqual(String firstMethodSyntax, String secondMethodSyntax) {
        String trimmedMethodSyntax1 = Trimmer.compressSeparators(firstMethodSyntax);
        String trimmedMethodSyntax2 = Trimmer.compressSeparators(secondMethodSyntax);
        return trimmedMethodSyntax1.equals(trimmedMethodSyntax2);
    }

    private void removeMethod(Method methodToRemove, ClassContent classContent) {
        updateSeparatorsOfRemovedMethodNeighbours(methodToRemove, classContent);
        classContent.getClassContentComponents().remove(methodToRemove);
    }

    private void updateSeparatorsOfRemovedMethodNeighbours(Method methodToRemove, ClassContent classContent) {
        int indexOfRemovedMethod = classContent.getClassContentComponents().indexOf(methodToRemove);
        if (indexOfRemovedMethod > 0) {
            String backSeparatorOfRemovedMethod = methodToRemove.getBackSeparator();
            classContent.getClassContentComponents().get(indexOfRemovedMethod - 1).setBackSeparator(backSeparatorOfRemovedMethod);
        }
    }
}
