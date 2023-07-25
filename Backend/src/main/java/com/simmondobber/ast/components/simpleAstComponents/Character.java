package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

public class Character extends SimpleAstComponent {

    public Character(String syntax, String backSeparator) {
        super(syntax, "", backSeparator);
    }

    public Character(String syntax, String frontSeparator, String backSeparator) {
        super(syntax, frontSeparator, backSeparator);
    }
}
