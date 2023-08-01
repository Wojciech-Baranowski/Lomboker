package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Package extends ComplexAstComponent {

    private static final String PACKAGE_KEYWORD = "package";

    private Keyword keyword;
    private Path path;
    private Character semicolon;

    public Package(Keyword keyword, Path path, Character semicolon) {
        this.keyword = keyword;
        this.path = path;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.keyword, this.path, this.semicolon);
    }

    @Override
    public String getFrontSeparator() {
        return this.keyword.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.keyword.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        return this.semicolon.getBackSeparator();
    }

    @Override
    public void setBackSeparator(String separator) {
        this.semicolon.setBackSeparator(separator);
    }
}
