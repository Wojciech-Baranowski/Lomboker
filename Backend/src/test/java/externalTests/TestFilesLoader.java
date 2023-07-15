package externalTests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestFilesLoader {

    private static final String JAVA_EXT = "java";
    private final String directory;

    public TestFilesLoader(String directory) {
        this.directory = directory;
    }

    public List<String> loadTestFiles() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            if (classLoader.getResource(this.directory) == null) {
                return new ArrayList<>();
            } else {
                return Files.walk(Paths.get(Objects.requireNonNull(classLoader.getResource(this.directory)).toURI()))
                        .filter(this::isJavaFile)
                        .map(this::readFileContent)
                        .toList();
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFileContent(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isJavaFile(Path path) {
        return Files.isRegularFile(path) && path.toString().endsWith(JAVA_EXT);
    }
}
