package com.simmondobber.ast.components.complexAstComponents.class_;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.class_.preamble.ClassPreamble;
import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.simpleAstComponents.ClassTypeKeyword;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Class extends ComplexAstComponent {

    private final ClassPreamble classPreamble;
    private final ClassTypeKeyword classTypeKeyword;
    private final Name name;
    private final Generic generic;
    private final Extension extension;
    private final ClassBody classBody;
    private final Separator separator;

    public Class(ClassPreamble classPreamble, ClassTypeKeyword classTypeKeyword, Name name, Generic generic, Extension extension, ClassBody classBody, Separator separator) {
        this.classPreamble = classPreamble;
        this.classTypeKeyword = classTypeKeyword;
        this.name = name;
        this.generic = generic;
        this.extension = extension;
        this.classBody = classBody;
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.classPreamble, this.classTypeKeyword, this.name, this.generic, this.extension, this.classBody, this.separator);
    }
}
