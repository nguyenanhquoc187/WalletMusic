package com.walletmusic.utils;

import com.walletmusic.constant.SystemConstant;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static String getImageSong(String filePath) {
        String imageSaved = null;
        try {
            // Đường dẫn tới file mp3
            File mp3File = new File(filePath);

            // Đọc thông tin ID3 tag của file mp3
            AudioFile audioFile = AudioFileIO.read(mp3File);
            Tag tag = audioFile.getTag();
            // Lấy hình ảnh từ ID3 tag
            byte[] imageData = tag.getFirstArtwork().getBinaryData();

            // Lưu hình ảnh dưới dạng jpg vào thư mục image
            String fileName = mp3File.getName().replaceFirst("[.][^.]+$", "") + System.currentTimeMillis();
            fileName = fileName.replaceAll("\\s+","-");
            File imageFile = new File(SystemConstant.UPLOAD_IMG_SONG_DIRECTORY + File.separator + fileName + ".jpg");
            imageSaved = "/template/web/assets/img/songs/" + fileName;
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageData)), "jpg", imageFile);
        } catch (IOException ex) {
            Logger.getLogger(AudioUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AudioUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageSaved+".jpg";
    }
}
