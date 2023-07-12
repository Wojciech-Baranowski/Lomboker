package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

import java.util.List;

public class ClassTypeKeyword extends SimpleAstComponent {

    public final static List<String> KEYWORDS = List.of("class", "interface", "enum", "record", "@interface");

    public ClassTypeKeyword(String syntax, String separator) {
        super(syntax, separator);
    }
}
