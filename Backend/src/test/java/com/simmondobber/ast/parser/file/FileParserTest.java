package com.simmondobber.ast.parser.file;

import com.simmondobber.ast.components.complexAstComponents.file.File;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        //Then
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

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
