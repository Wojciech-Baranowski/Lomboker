package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Annotation extends ComplexAstComponent implements PreambleComponent {

    private Character at;
    private Path path;
    private Args args;

    public Annotation(Character at, Path path, Args args) {
        this.at = at;
        this.path = path;
        this.args = args;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        if (this.args == null) {
            return List.of(this.at, this.path);
        } else {
            return List.of(this.at, this.path, this.args);
        }
    }

    @Override
    public String getFrontSeparator() {
        return this.at.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.at.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        if (this.args == null) {
            return this.path.getBackSeparator();
        } else {
            return this.args.getBackSeparator();
        }
    }

    @Override
    public void setBackSeparator(String separator) {
        if (this.args == null) {
            this.path.setBackSeparator(separator);
        } else {
            this.args.setBackSeparator(separator);
        }
    }
}
