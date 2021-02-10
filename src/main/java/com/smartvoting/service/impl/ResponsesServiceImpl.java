package com.smartvoting.service.impl;

import com.smartvoting.dto.ResponsesDTO;
import com.smartvoting.entity.Guest;
import com.smartvoting.entity.Responses;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.GuestRepository;
import com.smartvoting.repository.ResponsesRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.service.IResponsesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class ResponsesServiceImpl implements IResponsesService {

    @Autowired
    ResponsesRepository responsesRepository;

    @Autowired
    StatementRepository statementRepository;

    @Autowired
    GuestRepository guestRepository;

    @Override
    public Single<Object> addResponses(ResponsesDTO responsesDTO) {
        return Single.create(
                singleSubscriber -> {
                    try {
                        Responses addedResponses = responsesRepository.save(toResponses(responsesDTO));
                        singleSubscriber.onSuccess(addedResponses);
                    } catch (Exception e){
                        singleSubscriber.onError(e);
                    }
                }
        );
    }

    public Responses toResponses(ResponsesDTO responsesDTO) {
        Responses addedResponses = new Responses();
        BeanUtils.copyProperties(responsesDTO, addedResponses);
        Statement statement = statementRepository.findById(responsesDTO.getStatementId()).get();
        Guest guest = guestRepository.findById(responsesDTO.getGuestId()).get();
        addedResponses.setGuest(guest);
        addedResponses.setStatement(statement);
        return addedResponses;
    }
}
