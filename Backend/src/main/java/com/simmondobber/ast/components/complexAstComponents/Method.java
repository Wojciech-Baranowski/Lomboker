package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Method extends ComplexAstComponent implements ClassContentComponent {

    private final Preamble preamble;
    private final Type type;
    private final Name name;
    private final Args args;
    private final Throws throws_;
    private final MethodBody methodBody;
    private final Character semicolon;

    public Method(Preamble preamble, Type type, Name name, Args args, Throws throws_, MethodBody methodBody, Character semicolon) {
        this.preamble = preamble;
        this.type = type;
        this.name = name;
        this.args = args;
        this.throws_ = throws_;
        this.methodBody = methodBody;
        this.semicolon = semicolon;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> childComponents = new ArrayList<>();
        childComponents.add(this.preamble);
        childComponents.add(this.type);
        if (this.name != null) {
            childComponents.add(this.name);
        }
        childComponents.add(this.args);
        if (this.throws_ != null) {
            childComponents.add(this.throws_);
        }
        if (this.methodBody != null) {
            childComponents.add(this.methodBody);
        }
        if (this.semicolon != null) {
            childComponents.add(this.semicolon);
        }
        return childComponents;
    }

    @Override
    public String getFrontSeparator() {
        return this.preamble.getFrontSeparator();
    }

    @Override
    public String getBackSeparator() {
        if (this.methodBody == null) {
            return this.semicolon.getBackSeparator();
        } else {
            return this.methodBody.getBackSeparator();
        }
    }
}
