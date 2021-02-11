package com.smartvoting.repository;

import com.smartvoting.entity.Guest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest,String> {


//    @EntityGraph(value = "Room.roomId", type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "select * from guest where room_id = ?1", nativeQuery = true)
    List<Guest> findByRoom(String roomId);

}
