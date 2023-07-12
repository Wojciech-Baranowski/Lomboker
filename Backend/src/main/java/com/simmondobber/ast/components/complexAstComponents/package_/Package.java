package com.simmondobber.ast.components.complexAstComponents.package_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Package extends ComplexAstComponent {

    private static final String PACKAGE_KEYWORD = "package";

    private final Keyword keyword;
    private final Name name;
    private final Character semicolon;
    private final Separator separator;

    public Package(Name name, Separator separator) {
        this.keyword = new Keyword(PACKAGE_KEYWORD);
        this.name = name;
        this.semicolon = new Character(";");
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.name, this.semicolon, this.separator);
    }
}
