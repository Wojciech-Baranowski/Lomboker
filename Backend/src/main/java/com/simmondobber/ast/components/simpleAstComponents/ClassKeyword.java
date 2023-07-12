package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

import java.util.List;

public class ClassKeyword extends SimpleAstComponent {

    public final static List<String> KEYWORDS = List.of("abstract", "sealed", "final", "non-sealed", "public");

    public ClassKeyword(String syntax, String separator) {
        super(syntax, separator);
    }
}
