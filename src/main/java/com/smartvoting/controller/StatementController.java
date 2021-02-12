package com.smartvoting.controller;

import com.smartvoting.dto.StatementDTO;
import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.List;

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

    @PostMapping(value = "/addStatement")
    Single<Object> addStatement2(@RequestBody StatementDTO statementDTO){
        Statement statement = new Statement();
        BeanUtils.copyProperties(statementDTO, statementDTO);
        statement.setRoomId(statementDTO.getRoomId());
        return iStatementService.addStatement(statementDTO)
                .subscribeOn(Schedulers.io());
    }

    @GetMapping(value="/displayStatement/{roomId}")
    Single<List<Statement>> displayStatement(@PathVariable("roomId") String roomId){
        System.out.println("Inside display statement");
        return iStatementService.displayStatement(roomId)
                .subscribeOn(Schedulers.io());
    }


}
