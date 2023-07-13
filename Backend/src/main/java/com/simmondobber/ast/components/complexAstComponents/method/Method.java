package com.simmondobber.ast.components.complexAstComponents.method;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.complexAstComponents.method.preamble.MethodPreamble;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;

import java.util.List;

public class Method extends ComplexAstComponent {

    private final MethodPreamble methodPreamble;
    private final Type type;
    private final Name name;
    private final Args args;
    private final MethodBody methodBody;
    private final Character semicolon;

    public Method(MethodPreamble methodPreamble, Type type, Name name, Args args, MethodBody methodBody, Character semicolon) {
        this.methodPreamble = methodPreamble;
        this.type = type;
        this.name = name;
        this.args = args;
        this.methodBody = methodBody;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.methodBody == null) {
            return List.of(this.methodPreamble, this.type, this.name, this.args, this.semicolon);
        } else if (this.semicolon == null) {
            return List.of(this.methodPreamble, this.type, this.name, this.args, this.methodBody);
        }
        return List.of(this.methodPreamble, this.type, this.name, this.args, this.methodBody, this.semicolon);
    }
}
