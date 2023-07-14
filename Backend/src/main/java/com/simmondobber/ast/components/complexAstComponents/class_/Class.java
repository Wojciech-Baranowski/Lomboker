package com.simmondobber.ast.components.complexAstComponents.class_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.complexAstComponents.preamble.Preamble;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Class extends ComplexAstComponent implements ClassContentComponent {

    private final Preamble preamble;
    private final Keyword classTypeKeyword;
    private final Name name;
    private final Generic generic;
    private final Extension extension;
    private final ClassBody classBody;

    public Class(Preamble preamble, Keyword classTypeKeyword, Name name, Generic generic, Extension extension, ClassBody classBody) {
        this.preamble = preamble;
        this.classTypeKeyword = classTypeKeyword;
        this.name = name;
        this.generic = generic;
        this.extension = extension;
        this.classBody = classBody;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.generic == null && this.extension == null) {
            return List.of(this.preamble, this.classTypeKeyword, this.name, this.classBody);
        } else if (this.generic == null) {
            return List.of(this.preamble, this.classTypeKeyword, this.name, this.extension, this.classBody);
        } else if (this.extension == null) {
            return List.of(this.preamble, this.classTypeKeyword, this.name, this.generic, this.classBody);
        } else {
            return List.of(this.preamble, this.classTypeKeyword, this.name, this.generic, this.extension, this.classBody);
        }
    }
}
