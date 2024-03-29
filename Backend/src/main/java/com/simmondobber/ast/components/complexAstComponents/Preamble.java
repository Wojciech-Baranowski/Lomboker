package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Preamble extends ComplexAstComponent {

    private List<PreambleComponent> preambleComponents;

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

    @Override
    public String getFrontSeparator() {
        if (this.preambleComponents.isEmpty()) {
            return "";
        }
        return this.preambleComponents.get(0).getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        if (!this.preambleComponents.isEmpty()) {
            this.preambleComponents.get(0).setFrontSeparator(separator);
        }
    }

    @Override
    public String getBackSeparator() {
        if (this.preambleComponents.isEmpty()) {
            return "";
        }
        return this.preambleComponents.get(this.preambleComponents.size() - 1).getBackSeparator();
    }

    @Override
    public void setBackSeparator(String separator) {
        if (!this.preambleComponents.isEmpty()) {
            this.preambleComponents.get(this.preambleComponents.size() - 1).setBackSeparator(separator);
        }
    }
}
