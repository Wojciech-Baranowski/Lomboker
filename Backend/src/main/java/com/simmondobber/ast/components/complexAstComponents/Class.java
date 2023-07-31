package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.ClassExtension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Class extends ComplexAstComponent implements ClassContentComponent {

    private Preamble preamble;
    private Keyword classTypeKeyword;
    private Type type;
    private ClassExtension classExtension;
    private ClassBody classBody;

    public Class(Preamble preamble, Keyword classTypeKeyword, Type type, ClassExtension classExtension, ClassBody classBody) {
        this.preamble = preamble;
        this.classTypeKeyword = classTypeKeyword;
        this.type = type;
        this.classExtension = classExtension;
        this.classBody = classBody;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.classExtension == null) {
            return List.of(this.preamble, this.classTypeKeyword, this.type, this.classBody);
        } else {
            return List.of(this.preamble, this.classTypeKeyword, this.type, this.classExtension, this.classBody);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.preamble.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        return this.classBody.getBackSeparator();
    }
}
