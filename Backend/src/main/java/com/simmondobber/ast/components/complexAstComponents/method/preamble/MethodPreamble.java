package com.simmondobber.ast.components.complexAstComponents.method.preamble;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.annotation.Annotation;
import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.simpleAstComponents.MethodKeyword;

import java.util.ArrayList;
import java.util.List;

public class MethodPreamble extends ComplexAstComponent {

    private final List<MethodPreambleComponent> preambleComponents;

    public MethodPreamble(List<MethodPreambleComponent> preambleComponents) {
        this.preambleComponents = preambleComponents;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return new ArrayList<>(this.preambleComponents);
    }

    public List<Annotation> getAnnotations() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof Annotation)
                .map(component -> (Annotation) component)
                .toList();
    }

    public List<MethodKeyword> getMethodKeywords() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof MethodKeyword)
                .map(component -> (MethodKeyword) component)
                .toList();
    }

    public Generic getGeneric() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof Generic)
                .map(component -> (Generic) component)
                .findAny()
                .orElse(null);
    }
}
