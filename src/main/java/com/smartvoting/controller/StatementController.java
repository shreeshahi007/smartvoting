package com.smartvoting.controller;

import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.service.*;
import com.smartvoting.service.impl.StatementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/statement")
public class StatementController {

    @Autowired
    IStatementService iStatementService;

    @Autowired
    StatementRepository statementRepository;

//    @Autowired
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    IRoomService iRoomService;

    @PostMapping(value = "/addStatement/{roomId}")
    Single<Room> addStatement(@RequestBody Statement statement, @PathVariable String roomId){
        Room room = roomRepository.findById(roomId).get();
        room.getStatements().add(statement);
        return iRoomService.addRoom(room)
                .subscribeOn(Schedulers.io());
    }

    @PostMapping(value = "/addStatement2/")
    Single<Statement> addStatement2(@RequestBody Statement statement){
        return iStatementService.addStatement(statement)
                .subscribeOn(Schedulers.io());
    }



}
