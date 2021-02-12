package com.smartvoting.repository;

import com.smartvoting.entity.Responses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ResponsesRepository extends JpaRepository<Responses,String> {

    @Query(value = "select AVG(response_value) FROM responses where statement_id=?1", nativeQuery = true)
    double getMean(String statementId);

    @Query(value = "select * from responses where statement_id = ?1 and guest_id = ?2",nativeQuery = true)
    Responses findByStatementIdAndGuestId(String statementId, String guestId);

    @Query(value = "select * from responses where statement_id = ?1", nativeQuery = true)
    List<Responses> findByStatementId(String statementId);


}
