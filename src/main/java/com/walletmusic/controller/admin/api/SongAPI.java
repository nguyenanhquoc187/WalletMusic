package com.walletmusic.controller.admin.api;

import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.SongModel;
import com.walletmusic.service.ISongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.utils.AudioUtil;
import com.walletmusic.utils.HttpUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "api-admin-song", value = "/api-admin-song")
public class SongAPI extends HttpServlet {
    @Inject
    private ISongService songService;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        SongModel song = new SongModel();
        ArrayList<Integer> genresIdList = new ArrayList<>();
        ArrayList<Integer> artistIdList = new ArrayList<>();
        Integer albumId = null;
        String title =null;
        String timePlay = null;
        String lyrics = null;
        String image = null;
        String mediaUrl = null;


        if (!ServletFileUpload.isMultipartContent(request)) {
            // Nếu không phải thì trả về thông báo lỗi
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must have enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // Thiết lập các thông số để xử lý upload file
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setDefaultCharset("UTF-8");
        factory.setSizeThreshold(SystemConstant.MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(SystemConstant.MAX_FILE_SIZE);
        upload.setSizeMax(SystemConstant.MAX_REQUEST_SIZE);

        String uploadMediaPath = SystemConstant.UPLOAD_MEDIA_DIRECTORY;
        String uploadImgPath = SystemConstant.UPLOAD_IMG_SONG_DIRECTORY;
        // Tạo thư mục uploads nếu nó chưa tồn tại
        File uploadMediaDir = new File(uploadMediaPath);
        File uploadImgDir = new File(uploadImgPath);
        if (!uploadMediaDir.exists()) {
            uploadMediaDir.mkdir();
        }
        if (!uploadImgDir.exists()) {
            uploadImgDir.mkdir();
        }

        try {
            // Đọc dữ liệu được gửi từ client
            List<FileItem> formItems = upload.parseRequest(request);

            // media
            if (formItems != null && formItems.size() > 0) {
                // Lặp qua các trường trong form
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = null;

                        if (item.getFieldName().equals("mediaUrl")) {
                            mediaUrl =  "/template/web/assets/music/list-song/" + fileName;
                            filePath = uploadMediaDir + File.separator + fileName;
//                            timePlay = AudioUtil.getTotalTimeMp3File(item);
                        }

                        if (item.getFieldName().equals("image")) {
                            image =  "/template/web/assets/img/songs/" + fileName;
                            filePath = uploadImgDir + File.separator + fileName;
                        }
                        File storeFile = new File(filePath);
                        // Lưu trữ file vào đĩa cứng
                        item.write(storeFile);
                        if (mediaUrl != null) {
                            timePlay = AudioUtil.getTotalTimeMp3File(filePath);
                            if (title.equals("")) title = AudioUtil.getNameSong(filePath);
                        }
                    } else {
                        if (item.getFieldName().equals("genresIdList") ) {
                            genresIdList.add(Integer.parseInt(item.getString()));
                        }
                        if (item.getFieldName().equals("artistIdList") ) {
                            artistIdList.add(Integer.parseInt(item.getString()));
                        }
                        if (item.getFieldName().equals("albumId") && item.getSize() > 0) {
                            albumId = Integer.parseInt(item.getString());
                        }
                        if (item.getFieldName().equals("title")) {
                            title = item.getString();
                        }
                        if (item.getFieldName().equals("lyrics")) {
                            lyrics = item.getString();
                            lyrics = lyrics.replaceAll("\r\n\r\n","\r\n");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        song.setTitle(title);
        song.setGenresIdList(genresIdList);
        song.setArtistIdList(artistIdList);
        if (albumId != null) song.setAlbumId(albumId);
        song.setLyrics(lyrics);
        song.setTimePlay(timePlay);
        song.setImage(image);
        song.setMediaUrl(mediaUrl);
        song.setCountListen(0L);
        ObjectMapper mapper = new ObjectMapper();
        int id = songService.save(song);
        songService.saveToAI(id);
        mapper.writeValue(response.getOutputStream(),songService.findOne(id));

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        SongModel song = new SongModel();
        ArrayList<Integer> genresIdList = new ArrayList<>();
        ArrayList<Integer> artistIdList = new ArrayList<>();
        Integer id = null;
        Integer albumId = null;
        String title =null;
        String timePlay = null;
        String lyrics = null;
        String image = null;
        String mediaUrl = null;


//        if (!ServletFileUpload.isMultipartContent(request)) {
//            // Nếu không phải thì trả về thông báo lỗi
//            PrintWriter writer = response.getWriter();
//            writer.println("Error: Form must have enctype=multipart/form-data.");
//            writer.flush();
//            return;
//        }

        // Thiết lập các thông số để xử lý upload file
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setDefaultCharset("UTF-8");
        factory.setSizeThreshold(SystemConstant.MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(SystemConstant.MAX_FILE_SIZE);
        upload.setSizeMax(SystemConstant.MAX_REQUEST_SIZE);

        String uploadMediaPath = SystemConstant.UPLOAD_MEDIA_DIRECTORY;
        String uploadImgPath = SystemConstant.UPLOAD_IMG_SONG_DIRECTORY;
        // Tạo thư mục uploads nếu nó chưa tồn tại
        File uploadMediaDir = new File(uploadMediaPath);
        File uploadImgDir = new File(uploadImgPath);
        if (!uploadMediaDir.exists()) {
            uploadMediaDir.mkdir();
        }
        if (!uploadImgDir.exists()) {
            uploadImgDir.mkdir();
        }

        try {
            // Đọc dữ liệu được gửi từ client
            List<FileItem> formItems = upload.parseRequest(request);

            // media
            if (formItems != null && formItems.size() > 0) {
                // Lặp qua các trường trong form
                for (FileItem item : formItems) {
                    if (!item.isFormField() && item.getSize() != 0) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = null;

                        if (item.getFieldName().equals("mediaUrl") ) {
                            mediaUrl =  "/template/web/assets/music/list-song/" + fileName;
                            filePath = uploadMediaDir + File.separator + fileName;
//                            timePlay = AudioUtil.getTotalTimeMp3File(item);
                        }

                        if (item.getFieldName().equals("image") ) {
                            image =  "/template/web/assets/img/songs/" + fileName;
                            filePath = uploadImgDir + File.separator + fileName;
                        }
                        File storeFile = new File(filePath);
                        // Lưu trữ file vào đĩa cứng
                        item.write(storeFile);
                        if (mediaUrl != null) timePlay = AudioUtil.getTotalTimeMp3File(filePath);

                    } else {
                        if (item.getFieldName().equals("id") ) {
                            id = Integer.parseInt(item.getString());
                        }
                        if (item.getFieldName().equals("genresIdList") ) {
                            genresIdList.add(Integer.parseInt(item.getString()));
                        }
                        if (item.getFieldName().equals("artistIdList") ) {
                            artistIdList.add(Integer.parseInt(item.getString()));
                        }
                        if (item.getFieldName().equals("albumId") && item.getSize() > 0) {
                            albumId = Integer.parseInt(item.getString());
                        }
                        if (item.getFieldName().equals("title")) {
                            title = item.getString();
                        }
                        if (item.getFieldName().equals("lyrics")) {
                            lyrics = item.getString();
                            lyrics = lyrics.replaceAll("\r\n\r\n","\r\n");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        SongModel songOld = songService.findOne(id);
        if (mediaUrl == null) {
            mediaUrl = songOld.getMediaUrl();
            timePlay = songOld.getTimePlay();
        }
        if (image == null) image = songOld.getImage();
        song.setId(id);
        song.setTitle(title);
        song.setGenresIdList(genresIdList);
        song.setArtistIdList(artistIdList);
        if (albumId != null) song.setAlbumId(albumId);
        song.setLyrics(lyrics);
        song.setTimePlay(timePlay);
        song.setImage(image);
        song.setMediaUrl(mediaUrl);
        songService.update(song);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), song);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        SongModel songModel =  HttpUtil.of(request.getReader()).toModel(SongModel.class);
        songService.delete(songModel.getIds());
        for (Integer i: songModel.getIds() ) {
            songService.deleteSongAi(i);
        }
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
