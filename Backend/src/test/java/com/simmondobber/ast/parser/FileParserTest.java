package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.File;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.components.complexAstComponents.Package;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileParserTest {

    @Test
    public void parser_should_parse_class() {
        //Given
        String stringToParse = "package com.simmondobber.ast.parser.file;\nimport org.junit.jupiter.api.Assertions;\nimport org.junit.jupiter.api.Test;\npublic class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}$";
        String correctlyParsedString = "package com.simmondobber.ast.parser.file;\nimport org.junit.jupiter.api.Assertions;\nimport org.junit.jupiter.api.Test;\npublic class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        FileParser fileParser = new FileParser(new Pointer(stringToParse));

        //When
        File parsedFile = fileParser.parse();
        String parsedString = parsedFile.getSyntax();
        List<AstComponent> components = parsedFile.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Package.class, components.get(0));
        Assertions.assertInstanceOf(Import.class, components.get(1));
        Assertions.assertInstanceOf(Import.class, components.get(2));
        Assertions.assertInstanceOf(Class.class, components.get(3));
        Assertions.assertEquals("package com.simmondobber.ast.parser.file;\n", components.get(0).getSyntax());
        Assertions.assertEquals("import org.junit.jupiter.api.Assertions;\n", components.get(1).getSyntax());
        Assertions.assertEquals("import org.junit.jupiter.api.Test;\n", components.get(2).getSyntax());
        Assertions.assertEquals("public class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}", components.get(3).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_with_separators() {
        //Given
        String stringToParse = "package com.simmondobber.ast.parser.file;\n\t\t import org.junit.jupiter.api.Assertions;   \nimport org.junit.jupiter.api.Test;`123`\npublic class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}$";
        String correctlyParsedString = "package com.simmondobber.ast.parser.file;\n\t\t import org.junit.jupiter.api.Assertions;   \nimport org.junit.jupiter.api.Test;`123`\npublic class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        FileParser fileParser = new FileParser(new Pointer(stringToParse));

        //When
        File parsedFile = fileParser.parse();
        String parsedString = parsedFile.getSyntax();
        List<AstComponent> components = parsedFile.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Package.class, components.get(0));
        Assertions.assertInstanceOf(Import.class, components.get(1));
        Assertions.assertInstanceOf(Import.class, components.get(2));
        Assertions.assertInstanceOf(Class.class, components.get(3));
        Assertions.assertEquals("package com.simmondobber.ast.parser.file;\n\t\t ", components.get(0).getSyntax());
        Assertions.assertEquals("import org.junit.jupiter.api.Assertions;   \n", components.get(1).getSyntax());
        Assertions.assertEquals("import org.junit.jupiter.api.Test;`123`\n", components.get(2).getSyntax());
        Assertions.assertEquals("public class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}", components.get(3).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
