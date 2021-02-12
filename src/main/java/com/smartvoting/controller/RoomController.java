package com.smartvoting.controller;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.service.IRoomService;
import com.smartvoting.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    IRoomService iRoomService;

    @Autowired
    StatementController statementController;

    @Autowired
    RoomRepository roomRepository;

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

    @GetMapping(value = "/deleteRoom/{roomId}")
    Single<String> deleteRoom(@PathVariable String roomId){
        return iRoomService.deleteRoom(roomId)
                .subscribeOn(Schedulers.io());
    }

    @GetMapping(value = "/isAdmin")
    boolean isAdmin(@RequestBody GuestDTO guestDTO){
        Utils utils = new Utils();
        return utils.validatePassword(guestDTO, roomRepository);
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
