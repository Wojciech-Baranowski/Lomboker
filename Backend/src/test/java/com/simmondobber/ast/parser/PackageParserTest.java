package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Package;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PackageParserTest {

    @Test
    public void parser_should_parse_package() {
        //Given
        String stringToParse = "package com.simmondobber.ast.parser.package_;";
        String correctlyParsedString = "package com.simmondobber.ast.parser.package_;";
        PackageParser packageParser = new PackageParser(new Pointer(stringToParse));

        //When
        Package parsedPackage = packageParser.parse();
        String parsedString = parsedPackage.getSyntax();
        List<AstComponent> components = parsedPackage.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(Path.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("package ", components.get(0).getSyntax());
        Assertions.assertEquals("com.simmondobber.ast.parser.package_", components.get(1).getSyntax());
        Assertions.assertEquals(";", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_package_with_separators() {
        //Given
        String stringToParse = "package \n\n `123`\t com.simmondobber.ast.parser.package_   ;\t next";
        String correctlyParsedString = "package \n\n `123`\t com.simmondobber.ast.parser.package_   ;\t ";
        PackageParser packageParser = new PackageParser(new Pointer(stringToParse));

        //When
        Package parsedPackage = packageParser.parse();
        String parsedString = parsedPackage.getSyntax();
        List<AstComponent> components = parsedPackage.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Keyword.class, components.get(0));
        Assertions.assertInstanceOf(Path.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("package \n\n `123`\t ", components.get(0).getSyntax());
        Assertions.assertEquals("com.simmondobber.ast.parser.package_   ", components.get(1).getSyntax());
        Assertions.assertEquals(";\t ", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
