package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.lombokize.classElements.Field;
import com.simmondobber.lomboker.lombokize.classElements.Method;
import com.simmondobber.lomboker.lombokize.helpers.extractors.fieldExtractor.FieldExtractor;
import com.simmondobber.lomboker.lombokize.helpers.factories.GetterFactory;
import com.simmondobber.lomboker.lombokize.helpers.factories.SetterFactory;

import java.util.Collection;
import java.util.List;

public class MethodExtractor {

    private final FieldExtractor fieldExtractor;
    private final GetterFactory getterFactory;
    private final SetterFactory setterFactory;

    public MethodExtractor() {
        this.fieldExtractor = new FieldExtractor();
        this.getterFactory = new GetterFactory();
        this.setterFactory = new SetterFactory();
    }

    public List<Method> getGettersAndSettersContainedByClass(String classCode, boolean thisPrefix) {
        List<Method> gettersAndSetters = getGettersAndSettersBasedOnClassFieldsInClassCode(classCode, thisPrefix);
        return gettersAndSetters.stream()
                .filter(method -> classCode.contains(method.getMethodCode()))
                .toList();
    }

    private List<Method> getGettersAndSettersBasedOnClassFieldsInClassCode(String classCode, boolean thisPrefix) {
        List<Field> fields = this.fieldExtractor.getClassFieldsFromClassCode(classCode);
        return fields.stream()
                .map(field -> getGetterAndSetterForClassField(field, thisPrefix))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Method> getGetterAndSetterForClassField(Field field, boolean thisPrefix) {
        Method getter = this.getterFactory.createGetter(field, thisPrefix);
        Method setter = this.setterFactory.createSetter(field);
        return List.of(getter, setter);
    }
}
