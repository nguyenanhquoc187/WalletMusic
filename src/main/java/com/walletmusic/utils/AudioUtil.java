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
            duration = audioFile.getAudioHeader().getTrackLength();

        } catch (Exception e) {
            e.printStackTrace();

        }
        int minutes = duration/60;
        int seconds = duration%60;
        return String.format("%d:%02d",minutes,seconds);
    }
}
