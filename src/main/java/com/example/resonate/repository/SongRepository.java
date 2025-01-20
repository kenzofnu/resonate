package com.example.resonate.repository;


import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {

    Page<Song> findByAlbumId (Long albumid, Pageable pageable);

    @Query("SELECT s from Song s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%',:title,'%'))")
    Page<Song> searchByTitle(@Param("title") String title, Pageable pageable);
}
