package com.smartvoting.service;

import com.smartvoting.dto.ResponsesDTO;
import com.smartvoting.entity.Responses;
import com.smartvoting.responseDTO.StatsDTO;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public interface IResponsesService {
    Single<Object> addResponses(ResponsesDTO responses);

    double getMean(String statementId);

    double getMedian(String statementId);

    double getMode(String statementId);

    Single<StatsDTO> getStats(String statementId);

    StatsDTO getStatsHelper(String statementId);
}
