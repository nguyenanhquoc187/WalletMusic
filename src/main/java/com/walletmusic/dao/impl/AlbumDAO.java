package com.walletmusic.dao.impl;

import com.walletmusic.dao.IAlbumDAO;
import com.walletmusic.mapper.*;
import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class AlbumDAO extends AbstractDAO<AlbumModel> implements IAlbumDAO {
    @Override
    public AlbumModel findOne(int id) {
        String sql = "SELECT * from albums where id = ?";
        List<AlbumModel>  albums = query(sql,new AlbumMapper(), id);
        return albums.isEmpty() ? null : albums.get(0);
    }

    @Override
    public AlbumModel findOneWithArtistId(int id) {
        String sql = "SELECT album_id,group_concat(artist_id SEPARATOR ',') as artistIdList " +
                " FROM album_by " +
                " where album_id = ? " +
                " group by album_id;";
        List<AlbumModel> album = query(sql,new AlbumByArtistMapper(),id);
        return album.isEmpty() ? null : album.get(0);
    }

    @Override
    public List<AlbumModel> findAll() {
        String sql = "SELECT * from albums";
        List<AlbumModel> albums = query(sql,new AlbumMapper());
        return albums;
    }

    public List<AlbumModel> findAll(Pageble pageble) {
        String sql = "SELECT * FROM albums";
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"";
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql+= " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }
        List<AlbumModel> albumModelList = query(sql, new AlbumMapper());
        for (AlbumModel album : albumModelList) {
            album.setTotalSong(getTotalSong(album.getId()));
            album.setArtistIdList(findOneWithArtistId(album.getId()).getArtistIdList() );
        }
        return albumModelList;
    }

    @Override
    public List<AlbumModel> findAllByCountListen(String search) {
        String sql = "SELECT al.*, sum(count_listen) as count_listen FROM albums as al " +
                "left join songs as s " +
                "on al.id =s.album_id " +
                "where al.name like ? " +
                "group by al.id " +
                "order by count_listen desc " +
                "limit 5";
        String keyword = "%" + search +"%";
        List<AlbumModel> albums = query(sql,new AlbumMapper(), keyword);
        return albums;
    }

    @Override
    public List<AlbumModel> findAllByArtist(int artistId) {
        String sql = "select * from albums as al " +
                "inner join album_by as ab " +
                "on al.id = ab.album_id " +
                "where ab.artist_id = ? " +
                "group by al.id";
        List<AlbumModel> albums = query(sql,new AlbumMapper(),artistId);
        return albums;
    }

    @Override
    public List<AlbumModel> findAllBySearch(Pageble pageble) {
        String sql = "SELECT * FROM albums WHERE name LIKE ?";
        String keyword =  pageble.getSearchKeyWord() ;
        return query(sql,new AlbumMapper(),keyword);
    }


    @Override
    public int getTotalSong(int id) {
        String sql = "SELECT count(*) FROM songs where album_id = ?;";
        return count(sql,id);
    }


    @Override
    public int save(AlbumModel album) {
        String sql = "INSERT INTO albums(name,image,release_date) VALUES (?,?,?) " ;
        int id = insert(sql,album.getName(),album.getImage(),album.getReleaseDate());
        String sqlAlbumBy = "INSERT INTO album_by(artist_id, album_id) VALUES(?, ?)";
        for (Integer artistId : album.getArtistIdList()) {
            insert(sqlAlbumBy,artistId,id);
        }
        return id;
    }

    @Override
    public void update(AlbumModel newAlbum) {
        String sql = "UPDATE albums SET name = ?, image = ?, release_date = ? WHERE id = ? ";
        update(sql,newAlbum.getName(),newAlbum.getImage(),newAlbum.getReleaseDate(),newAlbum.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM albums WHERE id = ?";
        deleteAlbumBy(id);
        update(sql,id);
    }

    @Override
    public void deleteAlbumBy(int id) {
        String sql = "DELETE FROM album_by WHERE album_id = ?";
        update(sql,id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM albums";
        return count(sql);
    }
}
