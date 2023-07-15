package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Listing;

import java.util.List;

public class Throws extends ComplexAstComponent {

    private final Keyword keyword;
    private final Listing listing;

    public Throws(Keyword keyword, Listing listing) {
        this.keyword = keyword;
        this.listing = listing;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.listing);
    }
}
