package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;
import com.simmondobber.ast.components.complexAstComponents.method.preamble.MethodPreambleComponent;

import java.util.List;

public class MethodKeyword extends SimpleAstComponent implements MethodPreambleComponent {

    public final static List<String> KEYWORDS = List.of("abstract", "native", "private", "protected", "public", "static", "strictfp", "synchronized");

    public MethodKeyword(String syntax, String separator) {
        super(syntax, separator);
    }
}
