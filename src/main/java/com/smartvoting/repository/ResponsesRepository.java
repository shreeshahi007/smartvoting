package com.smartvoting.repository;

import com.smartvoting.entity.Responses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsesRepository extends JpaRepository<Responses,String> {

    @Query(value = "select AVG(response_value) FROM Responses where statement_id=?1", nativeQuery = true)
    double getMean(String statementId);


    @Query(value = "select ", nativeQuery = true)
    double getMedian(String statementId);

    @Query(value = "select TOP 1 response_value FROM Responses GROUP BY response_value ORDER BY COUNT(*) DESC where statement_id=?1", nativeQuery = true)
    double getMode(String statementId);

}
