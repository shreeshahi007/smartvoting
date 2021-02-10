package com.smartvoting.controller;

import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    IRoomService iRoomService;

    @Autowired
    StatementController statementController;

    @PostMapping(value = "/addRoom")
    Single<Room> addRoom(@RequestBody Room room){
        return iRoomService.addRoom(room)
                .subscribeOn(Schedulers.io());
    }

    @GetMapping(value = "/getRoom/{roomId}")
    Single<Room> getRoomDetails(@PathVariable String roomId){
        return iRoomService.getRoomDetails(roomId)
                .subscribeOn(Schedulers.io());
    }

    @GetMapping(value = "/test")
    Object test(){
        Room room = new Room();
        room.setRoomName("room1");
        room.setPassword("password");
        addRoom(room);

        Statement statement = new Statement();
        statement.setStatementContent("this is general statement");

        return statement;
    }
}
