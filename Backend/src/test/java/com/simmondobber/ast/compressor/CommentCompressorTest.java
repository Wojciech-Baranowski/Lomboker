package com.simmondobber.ast.compressor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CommentCompressorTest {

    @Test
    public void parser_should_compress_line_comments() {
        //Given
        String stringToCompress = """
                private String generateNextIdentifier() { //generates identifier of dictionary
                    String identifier = Integer.toString(this.sequence); //get as string
                    this.sequence++; //increments sequence
                    return "`" + identifier + '`'; //adds ` prefix and suffix (\\n\\n\\n)
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() { `0`
                    String identifier = Integer.toString(this.sequence); `1`
                    this.sequence++; `2`
                    return "`" + identifier + '`'; `3`
                }
                """;
        CompressedCode codeToCompress = new CompressedCode(stringToCompress);
        CommentCompressor compressor = new CommentCompressor(codeToCompress);

        //When
        CompressedCode compressedCode = compressor.compress();
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
                    return "`" + identifier + '`'; /*adds ` prefix and suffix (\\n\\n\\n)*/
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() { `0`
                    String identifier = Integer.toString(this.sequence); `1`
                    this.sequence++; `2`
                    return "`" + identifier + '`'; `3`
                }
                """;
        CompressedCode codeToCompress = new CompressedCode(stringToCompress);
        CommentCompressor compressor = new CommentCompressor(codeToCompress);

        //When
        CompressedCode compressedCode = compressor.compress();
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
                    return "`" + identifier + '`'; /**adds ` prefix and suffix (\\n\\n\\n)**/
                }
                """;
        String correctlyCompressedString = """
                private String generateNextIdentifier() { `0`
                    String identifier = Integer.toString(this.sequence); `1`
                    this.sequence++; `2`
                    return "`" + identifier + '`'; `3`
                }
                """;
        CompressedCode codeToCompress = new CompressedCode(stringToCompress);
        CommentCompressor compressor = new CommentCompressor(codeToCompress);

        //When
        CompressedCode compressedCode = compressor.compress();
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
}
