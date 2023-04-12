package com.walletmusic.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.walletmusic.dao.ISongDAO;
import com.walletmusic.model.GenresModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.ISongService;
import com.walletmusic.sort.Sorter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongService implements ISongService {
    @Inject
    private ISongDAO songDao ;
//    public SongService() {
//        songDao = new SongDAO();
//    }
    @Override
    public SongModel findOne(int id) {
        return songDao.findOne(id);
    }

    @Override
    public SongModel findOneWithArtistAndAlbum(int id) {
        return songDao.findOneWithArtistAndAlbum(id);
    }

    @Override
    public SongModel findOneWithArtistAndAlbumAndGenres(int id) {
        return songDao.findOneWithArtistAndAlbumAndGenres(id);
    }

    @Override
    public List<SongModel> findAll(Pageble pageble) {
        return songDao.findAll(pageble);
    }

    @Override
    public List<SongModel> findAllWithArtistAndAlbumAndGenres(Pageble pageble) {
        return songDao.findAllWithArtistAndAlbumAndGenres(pageble);
    }

    @Override
    public List<SongModel> findAllByTitleStart(Pageble pageble) {
        pageble.setSearchKeyWord(pageble.getSearchKeyWord() + "%");
        return songDao.findAllByTitle(pageble);
    }

    @Override
    public List<SongModel> findAllByTitle(Pageble pageble) {
        pageble.setSearchKeyWord("%" + pageble.getSearchKeyWord() + "%");
        return songDao.findAllByTitle(pageble);

    }

    @Override
    public List<SongModel> findAllByAlbumName(Pageble pageble) {
        pageble.setSearchKeyWord("%" + pageble.getSearchKeyWord() + "%");
        return songDao.findAllByAlbumName(pageble);
    }

    @Override
    public List<SongModel> findAllWithArtistAndAlbum(Pageble pageble) {
        return songDao.findAllWithArtistAndAlbum(pageble);
    }

    @Override
    public List<SongModel> findSongSuggest(int userId) {
        List<SongModel> songs = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:5000/recommend");
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = null;
        try {
            jsonBody = mapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse responseAPI = null;
        try {
            responseAPI = httpClient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Đọc kết quả JSON và chuyển đổi thành đối tượng Java
        HttpEntity responseEntity = responseAPI.getEntity();
        String jsonResponse = null;
        try {
            jsonResponse = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jsonResponse = jsonResponse.substring(jsonResponse.indexOf('['),jsonResponse.indexOf(']')+1);
        Gson gson = new Gson();
        int[] list_of_integers = gson.fromJson(jsonResponse, int[].class);
        for (int i : list_of_integers) {
            songs.add(findOneWithArtistAndAlbum(i));
        }
        return songs;
    }

    @Override
    public int save(SongModel song) {
        return songDao.save(song);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id: ids) {
            songDao.delete(id);
        }
    }

    @Override
    public void update(SongModel updateSong) {
        songDao.update(updateSong);
    }

    @Override
    public List<Integer> findAllAlbumId() {
        return songDao.findAllAlbumId();
    }

    @Override
    public List<SongModel> findAllByArtistName(Pageble pageble) {
        pageble.setSearchKeyWord("%" +pageble.getSearchKeyWord() +"%");
        return songDao.findAllByArtistName(pageble);
    }

    @Override
    public List<SongModel> findAllByCountListen() {
        Sorter sorter = new Sorter("count_listen", "desc" );
        Pageble pageble = new PageRequest(1,100, sorter);
        return songDao.findAllWithArtistAndAlbum(pageble);
    }

    @Override
    public List<SongModel> findSuggest() {
        Sorter sorter = new Sorter("count_listen", "desc" );
        Pageble pageble = new PageRequest(1,5, sorter);
        return songDao.findAllWithArtistAndAlbum(pageble);
    }

    @Override
    public List<SongModel> findAllByGenres(int genreId) {
        Sorter sorter = new Sorter("count_listen", "desc" );
        Pageble pageble = new PageRequest(1,100, sorter);
        List<SongModel> songs = songDao.findAllWithArtistAndAlbumAndGenres(pageble);
        List<SongModel> result = new ArrayList<>();
        for (SongModel song : songs) {
            for (GenresModel genre : song.getGenresList()) {
                if (genre.getId() == genreId) {
                    result.add(song);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<SongModel> findAllInPlaylist(int playlistId) {
        return songDao.findAllInPlaylist(playlistId);
    }

    @Override
    public List<SongModel> findAllInAlbum(int albumId) {
        return songDao.findAllInAlbum(albumId);
    }

    @Override
    public int getTotalItem() {
        return songDao.getTotalItem();
    }

    @Override
    public int getTotalItemSearchArtist(String keyword) {
        keyword = "%" + keyword + "%";
        return songDao.getTotalItemSearchArtist(keyword);
    }

    @Override
    public int getTotalItemSearchAlbum(String keyword) {
        keyword = "%" + keyword + "%";
        return songDao.getTotalItemSearchAlbum(keyword);
    }

    @Override
    public int getTotalItemSearchTitle(String keyword) {
        keyword = "%" + keyword + "%";
        return songDao.getTotalItemSearchTitle(keyword);
    }
}
