package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;

import java.util.List;

public class Args extends ComplexAstComponent {

    private final Character leftBracket;
    private final Listing listing;
    private final Character rightBracket;

    public Args(Character leftBracket, Listing listing, Character rightBracket) {
        this.leftBracket = leftBracket;
        this.listing = listing;
        this.rightBracket = rightBracket;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.listing == null) {
            return List.of(this.leftBracket, this.rightBracket);
        } else {
            return List.of(this.leftBracket, this.listing, this.rightBracket);
        }
    }
}
