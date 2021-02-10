package com.smartvoting.service.impl;

import com.smartvoting.entity.Responses;
import com.smartvoting.repository.ResponsesRepository;
import com.smartvoting.service.IResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class ResponsesServiceImpl implements IResponsesService {

    @Autowired
    ResponsesRepository responsesRepository;

    @Override
    public Single<Object> addResponses(Responses responses) {
        return Single.create(
                singleSubscriber -> {
                    Responses addedResponses=responsesRepository.save(responses);
                    singleSubscriber.onSuccess(addedResponses);
                }
        );
    }
}
