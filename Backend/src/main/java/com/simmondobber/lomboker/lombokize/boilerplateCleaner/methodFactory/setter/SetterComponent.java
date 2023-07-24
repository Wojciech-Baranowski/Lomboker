package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setter;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import lombok.Getter;

@Getter
public class SetterComponent {

    private final Field field;
    private final Method method;

    public SetterComponent(Field field, Method method) {
        this.field = field;
        this.method = method;
    }
}
