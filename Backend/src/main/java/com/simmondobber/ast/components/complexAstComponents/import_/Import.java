package com.simmondobber.ast.components.complexAstComponents.import_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Import extends ComplexAstComponent {

    private static final String IMPORT_KEYWORD = "import";

    private final Keyword keyword;
    private final Name name;
    private final Character semicolon;
    private final Separator separator;

    public Import(Name name, Separator separator) {
        this.keyword = new Keyword(IMPORT_KEYWORD);
        this.name = name;
        this.semicolon = new Character(";");
        this.separator = separator;
    }

    @Override
    protected List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.name, this.semicolon, this.separator);
    }
}
