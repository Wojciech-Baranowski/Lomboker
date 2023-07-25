package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.EnumValuesListing;
import lombok.Getter;

import java.util.List;

@Getter
public class EnumValues extends ComplexAstComponent {

    private final EnumValuesListing enumValuesListing;
    private final Character semicolon;

    public EnumValues(EnumValuesListing enumValuesListing, Character semicolon) {
        this.enumValuesListing = enumValuesListing;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.semicolon == null) {
            return List.of(this.enumValuesListing);
        } else {
            return List.of(this.enumValuesListing, this.semicolon);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.enumValuesListing.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        if (this.semicolon == null) {
            return this.enumValuesListing.getBackSeparator();
        } else {
            return this.semicolon.getBackSeparator();
        }
    }
}
