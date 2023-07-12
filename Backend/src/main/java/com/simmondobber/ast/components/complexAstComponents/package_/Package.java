package com.simmondobber.ast.components.complexAstComponents.package_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Package extends ComplexAstComponent {

    private static final String PACKAGE_KEYWORD = "package";

    private final Keyword keyword;
    private final Name name;
    private final Character semicolon;

    public Package(Keyword keyword, Name name, Character semicolon) {
        this.keyword = keyword;
        this.name = name;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.name, this.semicolon);
    }
}
