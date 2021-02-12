package com.smartvoting.service;

import com.smartvoting.dto.ResponsesDTO;
import com.smartvoting.entity.Responses;
import com.smartvoting.responseDTO.StatsDTO;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.List;

@Service
public interface IResponsesService {
    Single<Object> addResponses(ResponsesDTO responses);

    double getMean(String statementId);



    Single<StatsDTO> getStats(String statementId);

    StatsDTO getStatsHelper(String statementId);

    List<Integer> getResponsesByStatementId(String statementId);
}
