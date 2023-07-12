package com.simmondobber.ast.components.complexAstComponents.class_.preamble;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.annotation.Annotation;
import com.simmondobber.ast.components.simpleAstComponents.ClassKeyword;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.ArrayList;
import java.util.List;

public class ClassPreamble extends ComplexAstComponent {

    private final List<ClassPreambleComponent> preambleComponents;
    private final Separator separator;

    public ClassPreamble(List<ClassPreambleComponent> preambleComponents, Separator separator) {
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

    public List<ClassKeyword> getClassKeywords() {
        return this.preambleComponents.stream()
                .filter(component -> component instanceof ClassKeyword)
                .map(component -> (ClassKeyword) component)
                .toList();
    }
}
