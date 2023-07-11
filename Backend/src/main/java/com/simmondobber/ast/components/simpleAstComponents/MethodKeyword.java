package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

import java.util.List;

public class MethodKeyword extends SimpleAstComponent {

    public final static List<String> KEYWORDS = List.of("abstract", "native", "private", "protected", "public", "static", "strictfp", "synchronized");

    public MethodKeyword(String syntax) {
        super(syntax);
    }
}
