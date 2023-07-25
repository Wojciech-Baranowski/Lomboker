package com.simmondobber.lomboker.lombokize.boilerplateCleaner;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.comparator.AstComparator;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.ast.parser.complexComponentParser.ClassParser;
import com.simmondobber.ast.parser.complexComponentParser.FieldParser;
import com.simmondobber.ast.parser.complexComponentParser.MethodParser;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.MethodFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
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
        List<Class> classList = this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot());
        Collections.reverse(classList);
        classList.forEach(this::removeRedundantMethodsFromClass);
    }

    private void removeRedundantMethodsFromClass(Class clazz) {
        List<Field> classFields = this.astComponentFilter.getFieldListFromAstComponent(clazz);
        List<Method> classMethod = this.astComponentFilter.getMethodListFromAstComponent(clazz);
        List<Method> methodsBasedOnClassFields = this.methodFactory.generateMethodsBasedOnClassFields(classFields);
        List<Method> methodsToRemove = filterMethodsToRemove(classMethod, methodsBasedOnClassFields);
        removeMethods(clazz.getClassBody().getClassContent(), methodsToRemove);
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

    private void removeMethods(ClassContent classContent, List<Method> methodsToRemove) {
        String lastClassContentSeparator = classContent.getBackSeparator();
        classContent.getClassContentComponents().removeAll(methodsToRemove);
        if (!classContent.getBackSeparator().equals(lastClassContentSeparator)) {
            restoreSeparator(classContent, lastClassContentSeparator);
        }
    }

    private void restoreSeparator(ClassContent classContent, String lastClassContentSeparator) {
        int indexOfLastComponent = classContent.getClassContentComponents().size() - 1;
        ClassContentComponent lastComponent = classContent.getClassContentComponents().get(indexOfLastComponent);
        String lastComponentSyntax = StringUtils.removeEnd(lastComponent.getSyntax(), classContent.getBackSeparator()) + lastClassContentSeparator;
        if (lastComponent instanceof Field) {
            classContent.getClassContentComponents().set(indexOfLastComponent, new FieldParser(lastComponentSyntax).parse());
        } else if (lastComponent instanceof Method) {
            classContent.getClassContentComponents().set(indexOfLastComponent, new MethodParser(lastComponentSyntax).parse());
        } else if (lastComponent instanceof Class) {
            classContent.getClassContentComponents().set(indexOfLastComponent, new ClassParser(lastComponentSyntax).parse());
        }
    }
}
