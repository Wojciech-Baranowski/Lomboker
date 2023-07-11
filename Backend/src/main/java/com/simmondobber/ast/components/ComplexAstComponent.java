package com.simmondobber.ast.components;

import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
public abstract class ComplexAstComponent implements AstComponent {

    private final List<Interjection> interjections;

    protected abstract List<AstComponent> getChildAstComponents();

    public ComplexAstComponent(List<Interjection> interjections) {
        this.interjections = interjections;
    }

    @Override
    public String getSyntax() {
        return getChildComponentsSeparatedByInterjections().stream()
                .map(AstComponent::getSyntax)
                .collect(Collectors.joining());
    }

    private List<AstComponent> getChildComponentsSeparatedByInterjections() {
        List<AstComponent> childAstComponents = getChildAstComponents();
        return IntStream.range(0, childAstComponents.size())
                .boxed()
                .flatMap(index -> Stream.of(this.interjections.get(index), childAstComponents.get(index)))
                .toList();
    }
}
