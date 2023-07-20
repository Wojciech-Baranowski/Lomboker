package com.simmondobber.lomboker.lombokize.boilerplateCleaner;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.lomboker.lombokize.AstComponentFilter;

import java.util.List;

public class BoilerplateCleaner {

    private final AstComponentFilter astComponentFilter;

    public BoilerplateCleaner() {
        this.astComponentFilter = new AstComponentFilter();
    }

    public void removeRedundantMethods(Ast ast) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).forEach(this::removeRedundantMethods);
    }

    private void removeRedundantMethods(Class clazz) {
        List<Field> fields = this.astComponentFilter.getFieldListFromAstComponent(clazz);
        List<Method> methods = this.astComponentFilter.getMethodListFromAstComponent(clazz);
    }
}
