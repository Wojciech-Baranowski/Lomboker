package com.simmondobber.ast.components.complexAstComponents.method;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.complexAstComponents.method.preamble.MethodPreamble;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

import java.util.List;

public class Method extends ComplexAstComponent {

    private final MethodPreamble methodPreamble;
    private final Type type;
    private final Name name;
    private final Args args;
    private final MethodBody methodBody;
    private final Character semicolon;
    private final Separator separator;

    public Method(MethodPreamble methodPreamble, Type type, Name name, Args args, MethodBody methodBody, Separator separator) {
        this.methodPreamble = methodPreamble;
        this.type = type;
        this.name = name;
        this.args = args;
        this.methodBody = methodBody;
        this.semicolon = new Character(";");
        this.separator = separator;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        return List.of(this.methodPreamble, this.type, this.name, this.args, this.methodBody, this.semicolon, this.separator);
    }
}
