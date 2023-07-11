package com.simmondobber.ast.components.complexAstComponents.args;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import com.simmondobber.ast.components.simpleAstComponents.Listing;

import java.util.List;

public class Args extends ComplexAstComponent {

    private final Character leftBracket;
    private final Listing listing;
    private final Character rightBracket;

    public Args(List<Interjection> interjections, Listing listing) {
        super(interjections);
        this.leftBracket = new Character("(");
        this.listing = listing;
        this.rightBracket = new Character(")");
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftBracket, this.listing, this.rightBracket);
    }
}
