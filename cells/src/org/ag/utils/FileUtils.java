package org.ag.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class FileUtils {
    public static List<String> readFileToList(String filepath) throws IOException {
        return Files.readAllLines(new File(filepath).toPath(), Charset.forName("utf-8") );
    }

    public static List<String> readFileToList(String filepath, Charset encoding) throws IOException {
        return Files.readAllLines(new File(filepath).toPath(), encoding);
    }
}
