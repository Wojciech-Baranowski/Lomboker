package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.ThrowsListing;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Throws extends ComplexAstComponent {

    private Keyword keyword;
    private ThrowsListing throwsListing;

    public Throws(Keyword keyword, ThrowsListing throwsListing) {
        this.keyword = keyword;
        this.throwsListing = throwsListing;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.throwsListing);
    }

    @Override
    public String getFrontSeparator() {
        return this.keyword.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        return this.throwsListing.getBackSeparator();
    }
}
