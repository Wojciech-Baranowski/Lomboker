package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.ArgsListing;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Args extends ComplexAstComponent {

    private Character leftBracket;
    private ArgsListing argsListing;
    private Character rightBracket;

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

    @Override
    public String getFrontSeparator() {
        return this.leftBracket.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.leftBracket.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        return this.rightBracket.getBackSeparator();
    }

    @Override
    public void setBackSeparator(String separator) {
        this.rightBracket.setBackSeparator(separator);
    }
}
