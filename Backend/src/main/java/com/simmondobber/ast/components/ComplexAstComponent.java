package com.simmondobber.ast.components;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ComplexAstComponent implements AstComponent {

    public abstract List<AstComponent> getChildAstComponents();

    @Override
    public String getSyntax() {
        return getChildAstComponents().stream()
                .filter(Objects::nonNull)
                .map(AstComponent::getSyntax)
                .collect(Collectors.joining());
    }
}
