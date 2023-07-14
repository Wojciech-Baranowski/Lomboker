package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Method extends ComplexAstComponent implements ClassContentComponent {

    private final Preamble preamble;
    private final Type type;
    private final Name name;
    private final Args args;
    private final MethodBody methodBody;
    private final Character semicolon;

    public Method(Preamble preamble, Type type, Name name, Args args, MethodBody methodBody, Character semicolon) {
        this.preamble = preamble;
        this.type = type;
        this.name = name;
        this.args = args;
        this.methodBody = methodBody;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.methodBody == null) {
            return List.of(this.preamble, this.type, this.name, this.args, this.semicolon);
        } else {
            if (this.name == null) {
                return List.of(this.preamble, this.type, this.args, this.methodBody);
            } else {
                return List.of(this.preamble, this.type, this.name, this.args, this.methodBody);
            }
        }
    }
}
