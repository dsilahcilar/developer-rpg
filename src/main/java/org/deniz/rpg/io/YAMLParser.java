package org.deniz.rpg.io;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class YAMLParser {

    public List<String> parseList(String text, String field) {
        List<String> list = new ArrayList<>();
        String[] fields = field.split(Pattern.quote("."));
        int indexOfField = text.indexOf(fields[0] + ":") + fields[0].length() + 2;
        int indexOfNextField = text.indexOf("  " + fields[1] + ":", indexOfField) + fields[1].length() + 4;
        int indexOfLine;
        do {
            indexOfLine = text.indexOf("-", indexOfNextField);
            if (indexOfLine == -1) {
                break;
            }
            int indexOfEndLine = text.indexOf(System.lineSeparator(), indexOfLine);
            if (indexOfEndLine == -1) {
                indexOfEndLine = text.length();
            }
            String str = text.substring(indexOfLine + 2, indexOfEndLine);
            if (!str.isEmpty()) {
                list.add(str);
            }
            indexOfNextField = indexOfEndLine;
        } while (indexOfLine > 0);
        return list;
    }

    public String parse(String text, String field) {
        if (field.contains(".")) {
            return parseEmbeddedField(text, field);
        }
        int indexOfField = text.indexOf(field + ":") + field.length() + 2;
        int indexOfEndLine = text.indexOf(System.lineSeparator(), indexOfField);
        if (indexOfEndLine == -1) {
            indexOfEndLine = text.length();
        }
        return text.substring(indexOfField, indexOfEndLine);
    }

    public Integer parseInt(String text, String field) {
        try {
            return Integer.valueOf(parse(text, field));
        } catch (NumberFormatException ex) {
        }
        return null;
    }

    private String parseEmbeddedField(String text, String field) {
        String[] fields = field.split(Pattern.quote("."));
        final int indexOfFirstField = text.indexOf(fields[0] + ":");
        if (indexOfFirstField == -1) {
            return "";
        }
        int indexOfField = indexOfFirstField + fields[0].length() + 2;
        int indexOfNextField = text.indexOf("  " + fields[1] + ":", indexOfField) + fields[1].length() + 4;
        int indexOfEndLine = text.indexOf(System.lineSeparator(), indexOfNextField);
        if (indexOfEndLine == -1) {
            indexOfEndLine = text.length();
        }
        return text.substring(indexOfNextField, indexOfEndLine);
    }
}
