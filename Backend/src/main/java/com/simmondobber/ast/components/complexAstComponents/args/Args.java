package com.simmondobber.ast.components.complexAstComponents.args;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Args extends ComplexAstComponent {

    private final Character leftBracket;
    private final Listing listing;
    private final Character rightBracket;
    private final Separator separator;

    public Args(Listing listing, Separator separator) {
        this.leftBracket = new Character("(");
        this.listing = listing;
        this.rightBracket = new Character(")");
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.leftBracket, this.listing, this.rightBracket, this.separator);
    }
}
