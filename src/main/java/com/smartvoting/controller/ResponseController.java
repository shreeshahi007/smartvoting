package com.smartvoting.controller;

import com.smartvoting.entity.Responses;
import com.smartvoting.service.IResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@CrossOrigin
@RestController
@RequestMapping("/responses")
public class ResponseController {
    @Autowired
    IResponsesService iResponsesService;

    @PostMapping(value = "/addResponses")
    Single<Object> addResponses(@RequestBody Responses responses) {
        return iResponsesService.addResponses(responses)
                              .subscribeOn(Schedulers.io());
}
}
