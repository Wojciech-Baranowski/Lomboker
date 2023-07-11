package com.simmondobber.ast.components.complexAstComponents.class_.preamble;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.annotation.Annotation;
import com.simmondobber.ast.components.simpleAstComponents.ClassKeyword;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;

import java.util.List;

public class ClassPreamble extends ComplexAstComponent {

    private final List<ClassPreambleComponent> preambleComponents;

    public ClassPreamble(List<Interjection> interjections, List<ClassPreambleComponent> preambleComponents) {
        super(interjections);
        this.preambleComponents = preambleComponents;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return this.preambleComponents.stream()
                .map(component -> (AstComponent) component)
                .toList();
    }

    public List<Annotation> getAnnotations() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof Annotation)
                .map(component -> (Annotation) component)
                .toList();
    }

    public List<ClassKeyword> getClassKeywords() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof ClassKeyword)
                .map(component -> (ClassKeyword) component)
                .toList();
    }
}
