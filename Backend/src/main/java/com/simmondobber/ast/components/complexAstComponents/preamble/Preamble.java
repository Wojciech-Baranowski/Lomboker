package com.simmondobber.ast.components.complexAstComponents.preamble;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.annotation.Annotation;
import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;

import java.util.ArrayList;
import java.util.List;

public class Preamble extends ComplexAstComponent {

    private final List<PreambleComponent> preambleComponents;

    public Preamble(List<PreambleComponent> preambleComponents) {
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

    public List<Keyword> getKeywords() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof Keyword)
                .map(component -> (Keyword) component)
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
