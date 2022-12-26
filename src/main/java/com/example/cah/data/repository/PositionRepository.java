package com.example.cah.data.repository;

import com.example.cah.model.Position;
import com.example.cah.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Position SET endtag = 1 WHERE positionIdx = (select positionIdx from(select positionIdx from Position order by positionIdx desc limit 1) as c)",nativeQuery = true)
    void updateEndTag();

}
