package com.raio_be.raio_be.util;

public class SimpleSanitizer {
    public static String sanitize(String input) {
        if (input == null) return null;
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;")
            .replace("/", "&#x2F;");
    }
    public static String desanitize(String input) {
        if (input == null) return null;
        return input
            .replace("&#x2F;", "/")
            .replace("&#x27;", "'")
            .replace("&quot;", "\"")
            .replace("&gt;", ">")
            .replace("&lt;", "<")
            .replace("&amp;", "&");
    }
}
