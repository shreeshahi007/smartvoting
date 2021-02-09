package com.smartvoting.controller;

import com.smartvoting.entity.Statement;
import com.smartvoting.service.*;
import com.smartvoting.service.impl.StatementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/statement")
public class StatementController {

    @Autowired
    IStatementService iStatementService;

    @PostMapping(value = "/addStatement")
    Single<Statement> addStatement(@RequestBody Statement statement){
        return iStatementService.addStatement(statement)
                .subscribeOn(Schedulers.io());
    }




}
