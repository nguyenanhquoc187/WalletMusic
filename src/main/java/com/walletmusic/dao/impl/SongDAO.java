package com.walletmusic.dao.impl;

import com.walletmusic.dao.IAlbumDAO;
import com.walletmusic.dao.IArtistDAO;
import com.walletmusic.dao.IGenresDAO;
import com.walletmusic.dao.ISongDAO;
import com.walletmusic.mapper.RowMapper;
import com.walletmusic.mapper.SongByGenresMapper;
import com.walletmusic.mapper.SongMapper;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO extends AbstractDAO<SongModel> implements ISongDAO {
    @Inject
    private IArtistDAO artistDao;
    @Inject
    private  IAlbumDAO albumDao;
    @Inject
    private IGenresDAO genresDao;
    @Override
    public SongModel findOne(int id) {
        String sql = "SELECT s.id, s.title, s.lyrics, s.image as song_img, s.media_url, s.count_listen, s.time_play, " +
                "s.album_id,  GROUP_CONCAT(artist_id SEPARATOR ',') as artist_id_list " +
                "FROM songs as s " +
                "inner join song_by as sb " +
                "on s.id = sb.song_id " +
                "where s.id = ? " +
                "group by title" ;
        List<SongModel> songs = query(sql,new SongMapper(),id);
        return songs.isEmpty() ? null : songs.get(0);
    }

    @Override
    public SongModel findOneWithGenresId(int id) {
        String sql = "SELECT song_id, GROUP_CONCAT(genre_id SEPARATOR ',') as genresIdList" +
                " FROM genre_of" +
                " where song_id = ?" +
                " group by song_id";
        List<SongModel> songs = query(sql,new SongByGenresMapper(),id);
        return songs.isEmpty() ? null : songs.get(0);
    }

    @Override
    public SongModel findOneWithArtistAndAlbum(int id) {

        SongModel song = findOne(id);
        for (Integer i : song.getArtistIdList()) {
            song.getArtistList().add(artistDao.findOne(i) );
        }
        song.setAlbum(albumDao.findOne(song.getAlbumId()));
        return song;
    }

    @Override
    public SongModel findOneWithArtistAndAlbumAndGenres(int id) {
        SongModel song = findOneWithArtistAndAlbum(id);
        song.setGenresIdList(findOneWithGenresId(id).getGenresIdList());
        for (Integer i : song.getGenresIdList()) {
            song.getGenresList().add( genresDao.findOne(i) );
        }
        return song;
    }

    @Override
    public List<SongModel> findAll(Pageble pageble) {
        String sql = "SELECT s.id, s.title, s.lyrics, s.image as song_img, s.media_url, s.count_listen, s.time_play, " +
                "s.album_id,  GROUP_CONCAT(artist_id SEPARATOR ',') as artist_id_list " +
                "FROM songs as s " +
                "inner join song_by as sb " +
                "on s.id = sb.song_id " +
                "group by title" ;
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql += " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }
        List<SongModel> songs = query(sql.toString(), new SongMapper());
        return songs;
    }



    @Override
    public List<SongModel> findAllWithArtistAndAlbum(Pageble pageble) {
        List<SongModel> songs = findAll(pageble);
        for (SongModel song : songs) {
            for (Integer i : song.getArtistIdList()) {
                song.getArtistList().add(artistDao.findOne(i) );
            }
            song.setAlbum(albumDao.findOne(song.getAlbumId()));
        }
        return songs;
    }

    @Override
    public List<SongModel> findAllWithArtistAndAlbumAndGenres(Pageble pageble) {
        List<SongModel> songs = findAllWithArtistAndAlbum(pageble);
        for (SongModel song : songs) {
            SongModel songWithGenresId = findOneWithGenresId(song.getId());
            song.setGenresIdList(songWithGenresId.getGenresIdList());
            for (Integer i : song.getGenresIdList()) {
                song.getGenresList().add(genresDao.findOne(i));
            }
        }
        return songs;
    }

    @Override
    public List<SongModel> findAllByTitle(Pageble pageble) {
        String sql = " SELECT *, image as song_img FROM songs WHERE title LIKE ?";
        String keyWord =  "%" + pageble.getSearchKeyWord() + "%";
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql += " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }
        List<SongModel> songs = query(sql,new SongMapper(),keyWord);
        for (SongModel song : songs) {
            song.setArtistIdList(findArtistListById(song.getId()).getArtistIdList());
            for (Integer i : song.getArtistIdList()) {
                song.getArtistList().add(artistDao.findOne(i) );
            }
            song.setAlbum(albumDao.findOne(song.getAlbumId()));
        }
        return songs;
    }

    @Override
    public List<SongModel> findAllByAlbumName(Pageble pageble) {
        String sql = "SELECT distinct s.id, s.title, s.lyrics, s.image as song_img, s.media_url, s.count_listen, " +
                "s.time_play, s.album_id, al.name FROM songs as s " +
                "inner join albums as al " +
                "on s.album_id = al.id " +
                "inner join song_by as sb " +
                "on s.id = sb.song_id " +
                "where name like ?";
        String keyWord =  "%" + pageble.getSearchKeyWord() + "%";
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql += " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }
        List<SongModel> songs = query(sql,new SongMapper(),keyWord);
        for (SongModel song : songs) {
            song.setArtistIdList(findArtistListById(song.getId()).getArtistIdList());
            for (Integer i : song.getArtistIdList()) {
                song.getArtistList().add(artistDao.findOne(i) );
            }
            song.setAlbum(albumDao.findOne(song.getAlbumId()));
        }
        return songs;
    }


    @Override
    public List<SongModel> findAllByArtistName(Pageble pageble) {
        String sql = "SELECT distinct s.id, s.title, s.lyrics, s.image as song_img, s.media_url, s.count_listen, s.time_play, " +
                "s.album_id, al.name FROM songs as s " +
                "inner join song_by as sb " +
                "on s.id = sb.song_id " +
                "left join albums as al " +
                "on s.album_id = al.id " +
                "inner join artists as ar " +
                "on sb.artist_id = ar.id " +
                "where ar.name LIKE ?" ;
        String keyWord =  "%" + pageble.getSearchKeyWord() + "%";
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql += " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }

        List<SongModel> songs = query(sql,new SongMapper(),keyWord);
        for (SongModel song : songs) {
            song.setArtistIdList(findArtistListById(song.getId()).getArtistIdList());
            for (Integer i : song.getArtistIdList()) {
                song.getArtistList().add(artistDao.findOne(i) );
            }
            song.setAlbum(albumDao.findOne(song.getAlbumId()));
        }
        return songs;
    }



    @Override
    public SongModel findArtistListById( int id) {
        String sql = "SELECT GROUP_CONCAT(artist_id SEPARATOR ',') as artist_id_list " +
                "FROM song_by " +
                "where song_id = ?";
        List<SongModel> song = query(sql, new RowMapper<SongModel>() {
            @Override
            public SongModel mapRow(ResultSet resultSet) {
                SongModel song = new SongModel();
                try {
                    String listArtistId = resultSet.getString("artist_id_list");
                    for (String i : listArtistId.split(",")) {
                        song.getArtistIdList().add(Integer.parseInt(i));
                    }
                    return song;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        },id);
        return song.isEmpty() ? null : song.get(0);
    }


    @Override
    public int save(SongModel songModel) {
        String sql = "INSERT INTO songs(title,lyrics,image,media_url,count_listen,time_play,album_id) " +
                "VALUES (?,?,?,?,?,?,?)";
        Integer albumId = songModel.getAlbumId();
        if (songModel.getAlbumId() == 0) albumId = null;

        int id = insert(sql,songModel.getTitle(), songModel.getLyrics(), songModel.getImage(),
                songModel.getMediaUrl(), songModel.getCountListen(), songModel.getTimePlay(),albumId);
        String sqlSongBy = "INSERT INTO song_by(song_id,artist_id) VALUES (?,?)";
        String sqlGenreOf = "INSERT INTO genre_of(song_id,genre_id) VALUES (?,?)";
        ArrayList<Integer> genresIdList = songModel.getGenresIdList();
        ArrayList<Integer> artistIdList = songModel.getArtistIdList();
        for (Integer i : genresIdList) insert(sqlGenreOf,id,i);
        for (Integer i : artistIdList) insert(sqlSongBy,id,i);
        return id;
    }

    @Override
    public void update(SongModel updateSong) {
        String sql = "UPDATE songs SET title = ?, lyrics = ?, image = ?, media_url = ?, time_play = ?, album_id = ? WHERE id = ?";
        deleteGenreOf(updateSong.getId());
        deleteSongBy(updateSong.getId());
        String sqlSongBy = "INSERT INTO song_by(song_id,artist_id) VALUES (?,?)";
        String sqlGenreOf = "INSERT INTO genre_of(song_id,genre_id) VALUES (?,?)";
        ArrayList<Integer> genresIdList = updateSong.getGenresIdList();
        ArrayList<Integer> artistIdList = updateSong.getArtistIdList();
        Integer albumId = updateSong.getAlbumId();
        if (updateSong.getAlbumId() == 0) albumId = null;
        for (Integer i : genresIdList) insert(sqlGenreOf,updateSong.getId(),i);
        for (Integer i : artistIdList) insert(sqlSongBy,updateSong.getId(),i);
        update(sql,updateSong.getTitle(), updateSong.getLyrics(), updateSong.getImage(),
                updateSong.getMediaUrl(), updateSong.getTimePlay(),albumId,updateSong.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM songs WHERE id = ?";
        deleteGenreOf(id);
        deleteSongBy(id);
        update(sql, id);
    }

    @Override
    public void deleteGenreOf(int id) {
        String sql = "DELETE FROM genre_of WHERE song_id = ?";
        update(sql,id);
    }

    @Override
    public void deleteSongBy(int id) {
        String sql = "DELETE FROM song_by WHERE song_id = ?";
        update(sql,id);
    }

    @Override
    public List<Integer> findAllAlbumId() {
        String sql = "SELECT * FROM songs group by album_id";
        List<SongModel> songListGroupByAlbumId = query(sql, new SongMapper());
        List<Integer> albumIdList = new ArrayList<>();
        for (SongModel song : songListGroupByAlbumId) {
            albumIdList.add(song.getAlbumId());
        }
        return albumIdList;
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM songs";
        return count(sql);
    }

    @Override
    public int getTotalItemSearchArtist(String searchKeyWord) {
        String keyWord =  "%" + searchKeyWord + "%";
        String sql = "SELECT distinct count(*) FROM songs as s " +
                "inner join song_by as sb " +
                "on s.id = sb.song_id " +
                "left join albums as al " +
                "on s.album_id = al.id " +
                "inner join artists as ar " +
                "on sb.artist_id = ar.id " +
                "where ar.name LIKE ?"  ;

        return count(sql,keyWord);

    }

    @Override
    public int getTotalItemSearchAlbum(String searchKeyWord) {
        String keyWord =  "%" + searchKeyWord + "%";
        String sql = "SELECT distinct count(*) FROM songs as s " +
                "inner join albums as al " +
                "on s.album_id = al.id " +
                "inner join song_by as sb " +
                "on s.id = sb.song_id " +
                "where name like ?";
        return count(sql,keyWord);
    }

    @Override
    public int getTotalItemSearchTitle(String searchKeyWord) {
        String sql = " SELECT count(*) FROM songs WHERE title LIKE ?";
        String keyWord =  "%" + searchKeyWord + "%";
        return count(sql,keyWord);
    }
}
