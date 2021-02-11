package com.smartvoting.repository;

import com.smartvoting.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement,String> {

    @Query(value = "select * from statement where room_id=?1",nativeQuery = true)
    List<Statement> findByRoomId(String roomId);

    List<Statement> findByRoomIdOrderByDateCreatedDesc(String roomId);
}
