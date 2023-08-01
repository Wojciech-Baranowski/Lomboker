package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

import java.util.List;

public class ClassTypeKeyword extends SimpleAstComponent {

    public final static List<String> CLASS_TYPE_KEYWORDS = List.of("class", "interface", "enum", "record", "@interface");

    public ClassTypeKeyword(String syntax, String frontSeparator, String backSeparator) {
        super(syntax, frontSeparator, backSeparator);
    }
}
