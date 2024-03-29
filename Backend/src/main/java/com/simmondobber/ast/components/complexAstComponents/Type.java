package com.simmondobber.ast.components.complexAstComponents;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Type extends ComplexAstComponent {

    private Path path;
    private Generic generic;
    private List<ArrayBrackets> arrayBrackets;

    public Type(Path path, Generic generic, List<ArrayBrackets> arrayBrackets) {
        this.path = path;
        this.generic = generic;
        this.arrayBrackets = arrayBrackets;
    }

    @Override
    public List<AstComponent> getChildAstComponents() {
        List<AstComponent> components = new ArrayList<>();
        components.add(this.path);
        if (this.generic != null) {
            components.add(this.generic);
        }
        if (this.arrayBrackets != null) {
            components.addAll(this.arrayBrackets);
        }
        return components;
    }

    @Override
    public String getFrontSeparator() {
        return this.path.getFrontSeparator();
    }

    @Override
    public void setFrontSeparator(String separator) {
        this.path.setFrontSeparator(separator);
    }

    @Override
    public String getBackSeparator() {
        if (this.arrayBrackets.isEmpty()) {
            if (this.generic == null) {
                return this.path.getBackSeparator();
            } else {
                return this.generic.getBackSeparator();
            }
        } else {
            return this.arrayBrackets.get(this.arrayBrackets.size() - 1).getBackSeparator();
        }
    }

    @Override
    public void setBackSeparator(String separator) {
        if (this.arrayBrackets.isEmpty()) {
            if (this.generic == null) {
                this.path.setBackSeparator(separator);
            } else {
                this.generic.setBackSeparator(separator);
            }
        } else {
            this.arrayBrackets.get(this.arrayBrackets.size() - 1).setBackSeparator(separator);
        }
    }
}
