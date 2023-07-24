package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.getterFactory;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import lombok.Getter;

@Getter
public class GetterComponent {

    private final Field field;
    private final Method method;
    private final Method methodWithThis;

    public GetterComponent(Field field, Method method, Method methodWithThis) {
        this.field = field;
        this.method = method;
        this.methodWithThis = methodWithThis;
    }
}
