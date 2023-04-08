package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.service.IArtistService;
import com.walletmusic.utils.AudioUtil;
import com.walletmusic.utils.HttpUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "api-admin-artist", value = "/api-admin-artist")
public class ArtistAPI extends HttpServlet {

    @Inject
    private IArtistService artistService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ArtistModel artist = new ArtistModel();
        String name =null;
        Date birthDay = null;
        String gender = null;
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
        String uploadImgPath = SystemConstant.UPLOAD_IMG_ARTIST_DIRECTORY;
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
                            image =  "/template/web/assets/img/singer/" + fileName;
                            filePath = uploadImgDir + File.separator + fileName;
                        }
                        File storeFile = new File(filePath);
                        // Lưu trữ file vào đĩa cứng
                        item.write(storeFile);

                    } else {

                        if (item.getFieldName().equals("name")) {
                            name = item.getString();
                        }
                        if (item.getFieldName().equals("birthDay")) {
                            birthDay = Date.valueOf(item.getString());
                        }
                        if (item.getFieldName().equals("gender")) {
                            gender = item.getString();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }

        artist.setImage(image);
        artist.setName(name);
        artist.setGender(gender);
        artist.setBirthDay(birthDay);
        ObjectMapper mapper = new ObjectMapper();
        int id = artistService.save(artist);
        mapper.writeValue(response.getOutputStream(),artistService.findOne(id));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ArtistModel artist = new ArtistModel();
        Integer id = null;
        String name =null;
        Date birthDay = null;
        String gender = null;
        String image = null;



        // Thiết lập các thông số để xử lý upload file
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setDefaultCharset("UTF-8");
        factory.setSizeThreshold(SystemConstant.MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(SystemConstant.MAX_FILE_SIZE);
        upload.setSizeMax(SystemConstant.MAX_REQUEST_SIZE);
        String uploadImgPath = SystemConstant.UPLOAD_IMG_ARTIST_DIRECTORY;
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
                            image =  "/template/web/assets/img/singer/" + fileName;
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
                        if (item.getFieldName().equals("birthDay") && item.getSize() != 0) {
                            birthDay = Date.valueOf(item.getString());
                        }
                        if (item.getFieldName().equals("gender")) {
                            gender = item.getString();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }

        ArtistModel artistOld = artistService.findOne(id);

        if (image == null) image = artistOld.getImage();
        if (birthDay == null) birthDay = artistOld.getBirthDay();
        artist.setId(id);
        artist.setName(name);
        artist.setGender(gender);
        artist.setBirthDay(birthDay);
        artist.setImage(image);
        ObjectMapper mapper = new ObjectMapper();
        artistService.update(artist);
        mapper.writeValue(response.getOutputStream(),artist);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ArtistModel artistModel =  HttpUtil.of(request.getReader()).toModel(ArtistModel.class);
        artistService.delete(artistModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
