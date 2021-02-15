package com.smartvoting.repository;

import com.smartvoting.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest,String> {


//    @EntityGraph(value = "Room.roomId", type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "select * from guest where room_id = ?1", nativeQuery = true)
    List<Guest> findByRoomId(String roomId);


//    @Query("select" +
//            "new com.smartvoting.responseDTO.GuestWithResponseValues(g.guestName, g.guestId,g.photoUrl, r.responseValue )"+
//            "from Guest g inner join Responses r on g.guestId = r.Guest where r.statement = ?1"
//    )
//    List<GuestWithResponseValues> getGuestsResponses(String statementId);
}
