package com.simmondobber.ast.compressor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CharSequenceCompressorTest {

    @Test
    public void parser_should_compress_strings() {
        //Given
        String stringToCompress = "public void test(String s) {\n" +
                "        String str1 = \"21372137\";\n" +
                "        String str2 = \"f\";\n" +
                "        char c = \"\";\n" +
                "        int x = 2 + 2;\n" +
                "    }";
        String correctlyCompressedString = "public void test(String s) {\n" +
                "        String str1 = `0`;\n" +
                "        String str2 = `1`;\n" +
                "        char c = `2`;\n" +
                "        int x = 2 + 2;\n" +
                "    }";
        CompressedCode codeToCompress = new CompressedCode(stringToCompress);
        CharSequenceCompressor compressor = new CharSequenceCompressor(codeToCompress);

        //When
        CompressedCode compressedCode = compressor.compress();
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
        String stringToCompress = "public void test(String s) {\n" +
                "        char c1 = '\n';\n" +
                "        char c2 = 'f';\n" +
                "    }";
        String correctlyCompressedString = "public void test(String s) {\n" +
                "        char c1 = `0`;\n" +
                "        char c2 = `1`;\n" +
                "    }";
        CompressedCode codeToCompress = new CompressedCode(stringToCompress);
        CharSequenceCompressor compressor = new CharSequenceCompressor(codeToCompress);

        //When
        CompressedCode compressedCode = compressor.compress();
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
}
