package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.lombokize.codeElement.ClassField;
import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import com.simmondobber.lomboker.lombokize.helpers.factories.GetterFactory;
import com.simmondobber.lomboker.lombokize.helpers.factories.SetterFactory;

import java.util.Collection;
import java.util.List;

public class MethodsExtractor {

    private final FieldsExtractor fieldsExtractor;
    private final GetterFactory getterFactory;
    private final SetterFactory setterFactory;

    public MethodsExtractor() {
        this.fieldsExtractor = new FieldsExtractor();
        this.getterFactory = new GetterFactory();
        this.setterFactory = new SetterFactory();
    }

    public List<ClassMethod> getGettersAndSetterContainedByClass(String classCode) {
        List<ClassMethod> gettersAndSetters = getGettersAndSettersBasedOnClassFieldsInClassCode(classCode);
        return gettersAndSetters.stream()
                .filter(method -> classCode.contains(method.getMethodCode()))
                .toList();
    }

    private List<ClassMethod> getGettersAndSettersBasedOnClassFieldsInClassCode(String classCode) {
        List<ClassField> classFields = this.fieldsExtractor.getClassFieldsFromClassCode(classCode);
        return classFields.stream()
                .map(this::getGetterAndSetterForClassField)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ClassMethod> getGetterAndSetterForClassField(ClassField classField) {
        ClassMethod getter = this.getterFactory.createGetter(classField);
        ClassMethod setter = this.setterFactory.createSetter(classField);
        return List.of(getter, setter);
    }
}
