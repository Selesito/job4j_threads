package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private String content(Predicate<Character> filter) {
        String output = "";
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentWithoutUnicode() {
        return content(s -> s < 0x80);
    }

    public String getContent() {
        return content(s -> true);
    }
}
