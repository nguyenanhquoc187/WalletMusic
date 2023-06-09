package com.walletmusic.dao.impl;

import com.walletmusic.dao.IArtistDAO;
import com.walletmusic.mapper.ArtistMapper;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.sort.Sorter;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class ArtistDAO extends AbstractDAO<ArtistModel> implements IArtistDAO {
    @Override
    public ArtistModel findOne(int id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        List<ArtistModel> artistModelList =  query(sql,new ArtistMapper(),id);
        return artistModelList.isEmpty() ? null : artistModelList.get(0);
    }

    @Override
    public List<ArtistModel> findAll() {
        String sql = "SELECT * FROM artists";
        return query(sql,new ArtistMapper() );
    }

    @Override
    public List<ArtistModel> findAll(Pageble pageble) {
        String sql = "SELECT * FROM artists";
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"";
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql+= " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }
        List<ArtistModel> artistModelList = query(sql, new ArtistMapper());
        for (ArtistModel artist : artistModelList) {
            artist.setTotalSong(getTotalSong(artist.getId()));
            artist.setTotalAlbum(getTotalAlbum(artist.getId()));
        }
        return artistModelList;
    }

    @Override
    public List<ArtistModel> findAllByTotallisten() {
        String sql = "SELECT ar.id,ar.name,ar.birth_day, ar.gender,ar.image,sum(count_listen) as total_listen FROM artists as ar " +
                "inner join song_by as sb " +
                "on ar.id = sb.artist_id " +
                "inner join songs as s " +
                "on s.id = sb.song_id " +
                "group by ar.name order by ? ? limit ? ";
        List<ArtistModel> artists = query(sql,new ArtistMapper(),"total_listen","desc",5);
        return artists;
    }

    @Override
    public List<ArtistModel> findAllBySearch(Pageble pageble) {
        String sql = "SELECT * FROM artists WHERE name LIKE ?";
        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql+=" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"";
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql+= " LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"";
        }
        String keyword =  pageble.getSearchKeyWord() ;
        return query(sql,new ArtistMapper(),keyword);
    }

    @Override
    public int getTotalSong(int id) {
        String sql = "SELECT count(*) FROM song_by WHERE artist_id = ?";
        return count(sql,id);
    }

    @Override
    public int getTotalAlbum(int id) {
        String sql = "SELECT count(*) FROM album_by WHERE artist_id = ?";
        return count(sql,id);
    }

    @Override
    public int save(ArtistModel artist) {
        String sql = "INSERT INTO artists(name,birth_day,gender,image) VALUES (?,?,?,?) " ;
        int id = insert(sql,artist.getName(),artist.getBirthDay(),artist.getGender(),artist.getImage());
        return id;
    }

    @Override
    public void update(ArtistModel newArtist) {
        String sql = "UPDATE artists SET name = ?, birth_day = ?, gender = ?, image = ? WHERE id = ? ";
        update(sql,newArtist.getName(),newArtist.getBirthDay(),newArtist.getGender(),newArtist.getImage(),newArtist.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM artists WHERE id = ?";
        update(sql,id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM artists";
        return count(sql);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        String sql = "SELECT count(*) FROM artists WHERE name LIKE ?";

        return count(sql,keyword);
    }
}
