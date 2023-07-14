package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.File;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.components.complexAstComponents.Package;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.ArrayList;
import java.util.List;

public class FileParser extends AstParser {

    public FileParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public File parse() {
        Package package_ = new PackageParser(this.pointer).parse();
        List<Import> imports = new ArrayList<>();
        List<Class> classes = new ArrayList<>();
        while (this.pointer.lookupWord().equals("import")) {
            imports.add(new ImportParser(this.pointer).parse());
        }
        while (this.pointer.isNotEnd()) {
            imports.add(new ImportParser(this.pointer).parse());
        }
        return new File(package_, imports, classes);
    }
}
