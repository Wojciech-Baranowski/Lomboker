package com.simmondobber.ast.components.complexAstComponents.field.preamble;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.annotation.Annotation;
import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.simpleAstComponents.FieldKeyword;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.ArrayList;
import java.util.List;

public class FieldPreamble extends ComplexAstComponent {

    private final List<FieldPreambleComponent> preambleComponents;
    private final Separator separator;

    public FieldPreamble(List<FieldPreambleComponent> preambleComponents, Separator separator) {
        this.preambleComponents = preambleComponents;
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> components = new ArrayList<>(this.preambleComponents);
        components.add(this.separator);
        return components;
    }
    
    public List<Annotation> getAnnotations() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof Annotation)
                .map(component -> (Annotation) component)
                .toList();
    }

    public List<FieldKeyword> getFieldKeywords() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof FieldKeyword)
                .map(component -> (FieldKeyword) component)
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
