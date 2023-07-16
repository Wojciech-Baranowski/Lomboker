package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.ArgsListing;
import com.simmondobber.ast.components.simpleAstComponents.Character;

import java.util.List;

public class Args extends ComplexAstComponent {

    private final Character leftBracket;
    private final ArgsListing argsListing;
    private final Character rightBracket;

    public Args(Character leftBracket, ArgsListing argsListing, Character rightBracket) {
        this.leftBracket = leftBracket;
        this.argsListing = argsListing;
        this.rightBracket = rightBracket;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.argsListing == null) {
            return List.of(this.leftBracket, this.rightBracket);
        } else {
            return List.of(this.leftBracket, this.argsListing, this.rightBracket);
        }
    }
}
