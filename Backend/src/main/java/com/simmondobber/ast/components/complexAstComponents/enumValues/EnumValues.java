package com.simmondobber.ast.components.complexAstComponents.enumValues;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;

import java.util.List;

public class EnumValues extends ComplexAstComponent {

    private final Listing listing;
    private final Character semicolon;

    public EnumValues(Listing listing, Character semicolon) {
        this.listing = listing;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.listing, this.semicolon);
    }
}
