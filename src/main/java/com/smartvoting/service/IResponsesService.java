package com.smartvoting.service;

import com.smartvoting.dto.ResponsesDTO;
import com.smartvoting.entity.Responses;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public interface IResponsesService {
    Single<Object> addResponses(ResponsesDTO responses);
}
