package com.smartvoting.controller;

import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.repository.StatementRepository;
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

    @Autowired
    StatementRepository statementRepository;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping(value = "/addStatement")
    Single<Statement> addStatement(@RequestBody Statement statement){
//        Room room = roomRepository.findById("92ea33fd-6b19-4649-bb39-2bc9a750ce19").get();
//        statement.setRoom(room);
//        System.out.println(room);
        System.out.println("In add statement.. *********************");
        return iStatementService.addStatement(statement)
                .subscribeOn(Schedulers.io());
    }




}
