package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.ThrowsListing;
import lombok.Getter;

import java.util.List;

@Getter
public class Throws extends ComplexAstComponent {

    private final Keyword keyword;
    private final ThrowsListing throwsListing;

    public Throws(Keyword keyword, ThrowsListing throwsListing) {
        this.keyword = keyword;
        this.throwsListing = throwsListing;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.throwsListing);
    }
}
