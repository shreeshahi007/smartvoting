package com.smartvoting.controller;

import com.smartvoting.dto.ResponsesDTO;
import com.smartvoting.entity.Responses;
import com.smartvoting.service.IResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/responses")
public class ResponseController {
    @Autowired
    IResponsesService iResponsesService;

    @PostMapping(value = "/addResponses")
    Single<Object> addResponses(@RequestBody ResponsesDTO responsesDTO) {
        return iResponsesService.addResponses(responsesDTO)
                              .subscribeOn(Schedulers.io());
}
}
