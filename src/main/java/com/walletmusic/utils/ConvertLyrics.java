package com.walletmusic.utils;

public class ConvertLyrics {
    public static String convertOut(String lyrics) {
        lyrics = lyrics.replaceAll("[.]", "\n");
        return lyrics;
    }
    public static String convertIn(String lyrics) {
        lyrics = lyrics.replaceAll("\n", ". ");
        return lyrics;
    }
}
