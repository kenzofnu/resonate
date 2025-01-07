package com.example.resonate.repository;


import com.example.resonate.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SongRepository extends JpaRepository<Song,Long> {


}
