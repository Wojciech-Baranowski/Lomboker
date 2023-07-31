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
public class Import extends ComplexAstComponent {

    private Keyword importKeyword;
    private Keyword staticKeyword;
    private Path path;
    private Character semicolon;

    public Import(Keyword importKeyword, Keyword staticKeyword, Path path, Character semicolon) {
        this.importKeyword = importKeyword;
        this.staticKeyword = staticKeyword;
        this.path = path;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.staticKeyword == null) {
            return List.of(this.importKeyword, this.path, this.semicolon);
        } else {
            return List.of(this.importKeyword, this.staticKeyword, this.path, this.semicolon);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.importKeyword.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        return this.semicolon.getBackSeparator();
    }
}
