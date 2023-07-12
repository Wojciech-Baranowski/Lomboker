package com.simmondobber.ast.components.complexAstComponents.enumValues;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class EnumValues extends ComplexAstComponent {

    private final Listing listing;
    private final Character semicolon;
    private final Separator separator;

    public EnumValues(Listing listing, Separator separator) {
        this.listing = listing;
        this.semicolon = new Character(";");
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.listing, this.semicolon, this.separator);
    }
}
