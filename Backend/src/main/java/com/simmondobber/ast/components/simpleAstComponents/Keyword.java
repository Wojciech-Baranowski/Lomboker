package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;
import com.simmondobber.ast.components.complexAstComponents.PreambleComponent;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Keyword extends SimpleAstComponent implements PreambleComponent {

    public final static List<String> FIELD_KEYWORDS = List.of("final", "private", "protected", "public", "static", "transient", "volatile");
    public final static List<String> METHOD_KEYWORDS = List.of("abstract", "native", "private", "protected", "public", "static", "strictfp", "synchronized");
    public final static List<String> CLASS_KEYWORDS = List.of("abstract", "sealed", "final", "non-sealed", "public");
    public final static List<String> CLASS_TYPE_KEYWORDS = List.of("class", "interface", "enum", "record", "@interface");
    public final static List<String> PREAMBLE_KEYWORDS = Stream.of(FIELD_KEYWORDS, METHOD_KEYWORDS, CLASS_KEYWORDS).flatMap(Collection::stream).toList();

    public Keyword(String syntax, String separator) {
        super(syntax, separator);
    }
}
