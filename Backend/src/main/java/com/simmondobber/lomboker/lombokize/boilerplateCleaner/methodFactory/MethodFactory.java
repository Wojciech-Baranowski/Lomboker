package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.getter.GetterFactory;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setter.SetterFactory;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MethodFactory {

    public List<Method> generateMethodsBasedOnClassFields(List<Field> classFields, AnnotationsConfig annotationsConfig) {
        return classFields.stream()
                .map(field -> generateMethodsBasedOnField(field, annotationsConfig))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Method> generateMethodsBasedOnField(Field field, AnnotationsConfig annotationsConfig) {
        List<Method> generatedMethods = new ArrayList<>();
        if (annotationsConfig.isGetter()) {
            generatedMethods.addAll(generateGetters(field));
        }
        if (annotationsConfig.isSetter()) {
            generatedMethods.addAll(generateSetters(field));
        }
        return generatedMethods;
    }

    private List<Method> generateGetters(Field field) {
        GetterFactory getterFactory = new GetterFactory(field);
        return List.of(getterFactory.createGetter(), getterFactory.createGetterWithThis());
    }

    private List<Method> generateSetters(Field field) {
        SetterFactory setterFactory = new SetterFactory(field);
        return List.of(setterFactory.createSetter(), setterFactory.createSetterWithThis());
    }
}
