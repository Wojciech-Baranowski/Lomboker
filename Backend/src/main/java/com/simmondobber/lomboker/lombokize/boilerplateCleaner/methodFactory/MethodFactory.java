package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.getterFactory.GetterFactory;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setterFactory.SetterFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MethodFactory {

    public List<Method> generateMethodsBasedOnClassFields(List<Field> classFields) {
        return classFields.stream()
                .map(this::generateMethodsBasedOnField)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Method> generateMethodsBasedOnField(Field field) {
        List<Method> generatedMethods = new ArrayList<>();
        generatedMethods.addAll(generateGetters(field));
        generatedMethods.addAll(generateSetters(field));
        return generatedMethods;
    }

    private List<Method> generateGetters(Field field) {
        GetterFactory getterFactory = new GetterFactory(field);
        return List.of(getterFactory.createGetter(), getterFactory.createGetterWithThisPrefix());
    }

    private List<Method> generateSetters(Field field) {
        SetterFactory setterFactory = new SetterFactory(field);
        return List.of(setterFactory.createSetter(), setterFactory.createSetterWithThis());
    }
}
