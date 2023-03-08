package com.example.shop.repositories;

import com.example.shop.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query(value = "SELECT photo FROM photo WHERE photo_id=?1", nativeQuery = true)
    Optional<byte[]> getPhotoByPhotoId(int id);
}
