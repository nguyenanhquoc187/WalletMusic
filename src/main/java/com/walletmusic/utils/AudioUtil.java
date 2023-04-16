package com.walletmusic.utils;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AudioUtil {
    public static String getTotalTimeMp3File(String filePath) throws IOException, UnsupportedAudioFileException {

        int duration = 0;

        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath));
            String name = audioFile.getFile().getName();
            duration = audioFile.getAudioHeader().getTrackLength();

        } catch (Exception e) {
            e.printStackTrace();

        }
        int minutes = duration/60;
        int seconds = duration%60;
        return String.format("%d:%02d",minutes,seconds);
    }

    public static String getNameSong(String filePath) throws IOException, UnsupportedAudioFileException{
        String name = "";
        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath));
            name = audioFile.getFile().getName();
            name = name.substring(0,name.indexOf('.'));

        } catch (Exception e) {
            e.printStackTrace();

        }
        return name;
    }
}
