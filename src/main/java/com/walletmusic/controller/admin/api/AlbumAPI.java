package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.service.IAlbumService;
import com.walletmusic.service.IArtistService;
import com.walletmusic.service.ISongService;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "api-admin-album", value = "/api-admin-album")
public class AlbumAPI extends HttpServlet {

    @Inject
    private IAlbumService albumService;
    @Inject
    private ISongService songService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AlbumModel album = new AlbumModel();
        List<Integer> artistIdList = new ArrayList<>();
        String name = null;
        Date releaseDate = null;
        String image = null;


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
        String uploadImgPath = SystemConstant.UPLOAD_IMG_ALBUM_DIRECTORY;
        // Tạo thư mục uploads nếu nó chưa tồn tại
        File uploadImgDir = new File(uploadImgPath);

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

                        if (item.getFieldName().equals("image")) {
                            image =  "/template/web/assets/img/album/" + fileName;
                            filePath = uploadImgDir + File.separator + fileName;
                        }
                        File storeFile = new File(filePath);
                        // Lưu trữ file vào đĩa cứng
                        item.write(storeFile);

                    } else {

                        if (item.getFieldName().equals("name")) {
                            name = item.getString();
                        }
                        if (item.getFieldName().equals("releaseDate") && item.getSize() != 0) {
                            releaseDate = Date.valueOf(item.getString());
                        }
                        if (item.getFieldName().equals("artistIdList")) {
                            artistIdList.add(Integer.parseInt(item.getString()));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        album.setArtistIdList(artistIdList);
        album.setImage(image);
        album.setName(name);
        if (releaseDate == null) releaseDate = new Date(System.currentTimeMillis());
        album.setReleaseDate(releaseDate);
        ObjectMapper mapper = new ObjectMapper();
        int id = albumService.save(album);
        mapper.writeValue(response.getOutputStream(),albumService.findOne(id));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AlbumModel album = new AlbumModel();
        List<Integer> artistIdList = new ArrayList<>();
        Integer id = null;
        String name = null;
        Date releaseDate = null;
        String image = null;

        // Thiết lập các thông số để xử lý upload file
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setDefaultCharset("UTF-8");
        factory.setSizeThreshold(SystemConstant.MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(SystemConstant.MAX_FILE_SIZE);
        upload.setSizeMax(SystemConstant.MAX_REQUEST_SIZE);
        String uploadImgPath = SystemConstant.UPLOAD_IMG_ALBUM_DIRECTORY;
        // Tạo thư mục uploads nếu nó chưa tồn tại
        File uploadImgDir = new File(uploadImgPath);

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

                        if (item.getFieldName().equals("image")) {
                            image =  "/template/web/assets/img/album/" + fileName;
                            filePath = uploadImgDir + File.separator + fileName;
                        }
                        File storeFile = new File(filePath);
                        // Lưu trữ file vào đĩa cứng
                        item.write(storeFile);

                    } else {
                        if (item.getFieldName().equals("id")) {
                            id = Integer.parseInt(item.getString());
                        }

                        if (item.getFieldName().equals("name")) {
                            name = item.getString();
                        }
                        if (item.getFieldName().equals("releaseDate") && item.getSize() != 0) {
                            releaseDate = Date.valueOf(item.getString());
                        }
                        if (item.getFieldName().equals("artistIdList")) {
                            artistIdList.add(Integer.parseInt(item.getString()));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }

        AlbumModel albumOld = albumService.findOne(id);

        if (image == null) image =  albumOld.getImage();
        if (releaseDate == null) releaseDate = new Date(System.currentTimeMillis());
        album.setArtistIdList(artistIdList);
        album.setId(id);
        album.setName(name);
        album.setReleaseDate(releaseDate);
        album.setImage(image);
        ObjectMapper mapper = new ObjectMapper();
        albumService.update(album);
        mapper.writeValue(response.getOutputStream(),album);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AlbumModel albumModel =  HttpUtil.of(request.getReader()).toModel(AlbumModel.class);
        List<Integer> albumIdList = songService.findAllAlbumId();
        Integer[] ids = albumModel.getIds();
        boolean isDelete = true;
        for (Integer id : ids) {
            if (albumIdList.contains(id)) isDelete = false;
        }
        if (isDelete) {
            albumService.delete(ids);
            mapper.writeValue(response.getOutputStream(), "{}");
        }


    }
}
