package com.simmondobber.ast.components.complexAstComponents.enumValues;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import com.simmondobber.ast.components.simpleAstComponents.Listing;

import java.util.List;

public class EnumValues extends ComplexAstComponent {

    private final Listing listing;
    private final Character semicolon;

    public EnumValues(List<Interjection> interjections, Listing listing) {
        super(interjections);
        this.listing = listing;
        this.semicolon = new Character(";");
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.listing, this.semicolon);
    }
}
