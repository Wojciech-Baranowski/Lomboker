package com.simmondobber.ast.parser.package_;

import com.simmondobber.ast.components.complexAstComponents.package_.Package;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        //Then
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

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
