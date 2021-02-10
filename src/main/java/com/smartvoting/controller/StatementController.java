package com.smartvoting.controller;

import com.smartvoting.dto.StatementDTO;
import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.service.IRoomService;
import com.smartvoting.service.IStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@CrossOrigin
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

    @PostMapping(value = "/addStatement2")
    Single<Object> addStatement2(@RequestBody StatementDTO statementDTO){
        return iStatementService.addStatement(statementDTO)
                .subscribeOn(Schedulers.io());
    }




}
