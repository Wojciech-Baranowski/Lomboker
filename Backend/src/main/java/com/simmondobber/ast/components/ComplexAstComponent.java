package com.simmondobber.ast.components;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ComplexAstComponent implements AstComponent {

    protected abstract List<AstComponent> getChildAstComponents();

    @Override
    public String getSyntax() {
        return getChildAstComponents().stream()
                .map(AstComponent::getSyntax)
                .collect(Collectors.joining());
    }
}
