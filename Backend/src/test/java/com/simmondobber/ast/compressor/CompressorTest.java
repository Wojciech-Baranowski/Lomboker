package com.simmondobber.ast.compressor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CompressorTest {

    @Test
    public void parser_should_compress_strings() {
        //Given
        String stringToCompress = """
                public void test(String s) {
                    String str1 = "21372137";
                    String str2 = "f";
                    char c = "";
                    int x = 2 + 2;
                }
                """;
        String correctlyCompressedString = """
                public void test(String s) {
                    String str1 = `0`;
                    String str2 = `1`;
                    char c = `2`;
                    int x = 2 + 2;
                }
                """;
        CodeCompressor compressor = new CodeCompressor(stringToCompress);

        //When
        CompressedCode compressedCode = compressor.compressCode();
        String compressedString = compressedCode.getCode();
        Map<String, String> dictionary = compressedCode.getDictionary();

        //Then
        Assertions.assertEquals(3, dictionary.size());
        Assertions.assertTrue(dictionary.containsKey("`0`"));
        Assertions.assertTrue(dictionary.containsKey("`1`"));
        Assertions.assertTrue(dictionary.containsKey("`2`"));
        Assertions.assertEquals("\"21372137\"", dictionary.get("`0`"));
        Assertions.assertEquals("\"f\"", dictionary.get("`1`"));
        Assertions.assertEquals("\"\"", dictionary.get("`2`"));
        Assertions.assertEquals(correctlyCompressedString, compressedString);
    }

    @Test
    public void parser_should_compress_characters() {
        //Given
        String stringToCompress = """
                public void test(String s) {
                    char c1 = '\n';
                    char c2 = 'f';
                }
                """;
        String correctlyCompressedString = """
                public void test(String s) {
                    char c1 = `0`;
                    char c2 = `1`;
                }
                """;
        CodeCompressor compressor = new CodeCompressor(stringToCompress);

        //When
        CompressedCode compressedCode = compressor.compressCode();
        String compressedString = compressedCode.getCode();
        Map<String, String> dictionary = compressedCode.getDictionary();

        //Then
        Assertions.assertEquals(2, dictionary.size());
        Assertions.assertTrue(dictionary.containsKey("`0`"));
        Assertions.assertTrue(dictionary.containsKey("`1`"));
        Assertions.assertEquals("'\n'", dictionary.get("`0`"));
        Assertions.assertEquals("'f'", dictionary.get("`1`"));
        Assertions.assertEquals(correctlyCompressedString, compressedString);
    }

    @Test
    public void parser_should_compress_line_comments() {
        //Given
        String stringToCompress = """
                private String generateNextIdentifier() { //generates identifier of dictionary
                    String identifier = Integer.toString(this.sequence); //get as string
                    this.sequence++; //increments sequence
                    return 2; //adds ` prefix and suffix (\\n\\n\\n)
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() { `0`
                    String identifier = Integer.toString(this.sequence); `1`
                    this.sequence++; `2`
                    return 2; `3`
                }
                """;
        CodeCompressor compressor = new CodeCompressor(stringToCompress);

        //When
        CompressedCode compressedCode = compressor.compressCode();
        String compressedString = compressedCode.getCode();
        Map<String, String> dictionary = compressedCode.getDictionary();

        //Then
        Assertions.assertEquals(4, dictionary.size());
        Assertions.assertTrue(dictionary.containsKey("`0`"));
        Assertions.assertTrue(dictionary.containsKey("`1`"));
        Assertions.assertTrue(dictionary.containsKey("`2`"));
        Assertions.assertTrue(dictionary.containsKey("`3`"));
        Assertions.assertEquals("//generates identifier of dictionary", dictionary.get("`0`"));
        Assertions.assertEquals("//get as string", dictionary.get("`1`"));
        Assertions.assertEquals("//increments sequence", dictionary.get("`2`"));
        Assertions.assertEquals("//adds ` prefix and suffix (\\n\\n\\n)", dictionary.get("`3`"));
        Assertions.assertEquals(correctlyCompressedString, compressedString);
    }

    @Test
    public void parser_should_compress_block_comments() {
        //Given
        String stringToCompress = """
                private String generateNextIdentifier() { /*generates identifier of dictionary*/
                    String identifier = Integer.toString(this.sequence); /*get as string*/
                    this.sequence++; /*increments sequence*/
                    return 2; /*adds ` prefix and suffix (\\n\\n\\n)*/
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() { `0`
                    String identifier = Integer.toString(this.sequence); `1`
                    this.sequence++; `2`
                    return 2; `3`
                }
                """;
        CodeCompressor compressor = new CodeCompressor(stringToCompress);

        //When
        CompressedCode compressedCode = compressor.compressCode();
        String compressedString = compressedCode.getCode();
        Map<String, String> dictionary = compressedCode.getDictionary();

        //Then
        Assertions.assertEquals(4, dictionary.size());
        Assertions.assertTrue(dictionary.containsKey("`0`"));
        Assertions.assertTrue(dictionary.containsKey("`1`"));
        Assertions.assertTrue(dictionary.containsKey("`2`"));
        Assertions.assertTrue(dictionary.containsKey("`3`"));
        Assertions.assertEquals("/*generates identifier of dictionary*/", dictionary.get("`0`"));
        Assertions.assertEquals("/*get as string*/", dictionary.get("`1`"));
        Assertions.assertEquals("/*increments sequence*/", dictionary.get("`2`"));
        Assertions.assertEquals("/*adds ` prefix and suffix (\\n\\n\\n)*/", dictionary.get("`3`"));
        Assertions.assertEquals(correctlyCompressedString, compressedString);
    }

    @Test
    public void parser_should_parse_docs_comments() {
        //Given
        String stringToCompress = """
                private String generateNextIdentifier() { /**generates identifier of dictionary**/
                    String identifier = Integer.toString(this.sequence); /**get as string**/
                    this.sequence++; /**increments sequence**/
                    return 2; /**adds ` prefix and suffix (\\n\\n\\n)**/
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() { `0`
                    String identifier = Integer.toString(this.sequence); `1`
                    this.sequence++; `2`
                    return 2; `3`
                }
                """;
        CodeCompressor compressor = new CodeCompressor(stringToCompress);

        //When
        CompressedCode compressedCode = compressor.compressCode();
        String compressedString = compressedCode.getCode();
        Map<String, String> dictionary = compressedCode.getDictionary();

        //Then
        Assertions.assertEquals(4, dictionary.size());
        Assertions.assertTrue(dictionary.containsKey("`0`"));
        Assertions.assertTrue(dictionary.containsKey("`1`"));
        Assertions.assertTrue(dictionary.containsKey("`2`"));
        Assertions.assertTrue(dictionary.containsKey("`3`"));
        Assertions.assertEquals("/**generates identifier of dictionary**/", dictionary.get("`0`"));
        Assertions.assertEquals("/**get as string**/", dictionary.get("`1`"));
        Assertions.assertEquals("/**increments sequence**/", dictionary.get("`2`"));
        Assertions.assertEquals("/**adds ` prefix and suffix (\\n\\n\\n)**/", dictionary.get("`3`"));
        Assertions.assertEquals(correctlyCompressedString, compressedString);
    }

    @Test
    public void parser_should_parse_mixed_block_to_compress() {
        //Given
        String stringToCompress = """
                private String generateNextIdentifier() {
                    String identifier = Integer.toString(this.sequence);//That's a cast from "integer" to "string"
                    /*String weirdString = "There's and comment block /* and random quote \" inside";*/
                    this.sequence++;
                    return "`" + identifier + '`';
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() {
                    String identifier = Integer.toString(this.sequence);`0`
                    `1`
                    this.sequence++;
                    return `2` + identifier + `3`;
                }
                """;
        CodeCompressor compressor = new CodeCompressor(stringToCompress);

        //When
        CompressedCode compressedCode = compressor.compressCode();
        String compressedString = compressedCode.getCode();
        Map<String, String> dictionary = compressedCode.getDictionary();

        //Then
        Assertions.assertEquals(4, dictionary.size());
        Assertions.assertTrue(dictionary.containsKey("`0`"));
        Assertions.assertTrue(dictionary.containsKey("`1`"));
        Assertions.assertTrue(dictionary.containsKey("`2`"));
        Assertions.assertTrue(dictionary.containsKey("`3`"));
        Assertions.assertEquals("//That's a cast from \"integer\" to \"string\"", dictionary.get("`0`"));
        Assertions.assertEquals("/*String weirdString = \"There's and comment block /* and random quote \" inside\";*/", dictionary.get("`1`"));
        Assertions.assertEquals("\"`\"", dictionary.get("`2`"));
        Assertions.assertEquals("'`'", dictionary.get("`3`"));
        Assertions.assertEquals(correctlyCompressedString, compressedString);
    }
}
