package com.simmondobber.ast.components.complexAstComponents.import_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Interjection;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Import extends ComplexAstComponent {

    private static final String IMPORT_KEYWORD = "import";

    private final Keyword keyword;
    private final Name name;
    private final Character semicolon; 
    
    public Import(List<Interjection> interjections, Name name) {
        super(interjections);
        this.keyword = new Keyword(IMPORT_KEYWORD);
        this.name = name;
        this.semicolon = new Character(";");
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.name, this.semicolon);
    }
}
