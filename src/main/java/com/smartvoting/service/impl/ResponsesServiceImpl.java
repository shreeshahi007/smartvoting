package com.smartvoting.service.impl;

import com.smartvoting.dto.ResponsesDTO;
import com.smartvoting.entity.Guest;
import com.smartvoting.entity.Responses;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.GuestRepository;
import com.smartvoting.repository.ResponsesRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.responseDTO.StatsDTO;
import com.smartvoting.service.IResponsesService;
import com.smartvoting.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public double getMean(String statementId){
        return responsesRepository.getMean(statementId);
    }


    @Override
    public Single<StatsDTO> getStats(String statementId) {
        return Single.create(
                singleSubscriber -> {
                    try{
                        StatsDTO statsDTO = getStatsHelper(statementId);
                        singleSubscriber.onSuccess(statsDTO);
                    } catch (Exception e){
                        singleSubscriber.onError(e);
                    }
                }
        );
    }

    @Override
    public StatsDTO getStatsHelper(String statementId) {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setMean(getMean(statementId));

        Utils utils = new Utils();
        List<Integer> integerList =getResponsesByStatementId(statementId);
        statsDTO.setMedian(utils.getMedian(integerList));
        statsDTO.setMode(utils.getMode(integerList));
        return statsDTO;
    }

    @Override
    public List<Integer> getResponsesByStatementId(String statementId) {
        List<Responses> responsesList = responsesRepository.findByStatementId(statementId);
        List<Integer> list = new ArrayList<>();
        for (Responses responses : responsesList){
            list.add(responses.getResponseValue());
        }
        return list;
    }
}
