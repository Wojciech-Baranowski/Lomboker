package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;

import java.util.List;

public class Class extends ComplexAstComponent implements ClassContentComponent {

    private final Preamble preamble;
    private final Keyword classTypeKeyword;
    private final Type type;
    private final Extension extension;
    private final ClassBody classBody;

    public Class(Preamble preamble, Keyword classTypeKeyword, Type type, Extension extension, ClassBody classBody) {
        this.preamble = preamble;
        this.classTypeKeyword = classTypeKeyword;
        this.type = type;
        this.extension = extension;
        this.classBody = classBody;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.extension == null) {
            return List.of(this.preamble, this.classTypeKeyword, this.type, this.classBody);
        } else {
            return List.of(this.preamble, this.classTypeKeyword, this.type, this.extension, this.classBody);
        }
    }
}
