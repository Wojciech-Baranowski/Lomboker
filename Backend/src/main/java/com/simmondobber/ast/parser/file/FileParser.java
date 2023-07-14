package com.simmondobber.ast.parser.file;

import com.simmondobber.ast.components.complexAstComponents.class_.Class;
import com.simmondobber.ast.components.complexAstComponents.file.File;
import com.simmondobber.ast.components.complexAstComponents.import_.Import;
import com.simmondobber.ast.components.complexAstComponents.package_.Package;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.import_.ImportParser;
import com.simmondobber.ast.parser.package_.PackageParser;

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
