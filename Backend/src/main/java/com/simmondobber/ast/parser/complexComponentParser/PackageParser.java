package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Package;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.KeywordParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class PackageParser extends AstParser {

    public PackageParser(Pointer pointer) {
        super(pointer);
    }

    public PackageParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Package parse() {
        Keyword keyword = new KeywordParser(this.pointer).parse();
        Path path = new Path(this.pointer.getCompoundWord(), this.pointer.getLastSeparator(), this.pointer.getSeparator());
        Character semicolon = new CharacterParser(this.pointer).parse();
        return new Package(keyword, path, semicolon);
    }
}
