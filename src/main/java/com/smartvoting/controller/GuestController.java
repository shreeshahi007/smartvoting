package com.smartvoting.controller;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Guest;
import com.smartvoting.responseDTO.GuestWithResponseValuesDTO;
import com.smartvoting.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    IGuestService iGuestService;

    @PostMapping(value="/addGuest")
    Single<Guest> addGuest(@RequestBody GuestDTO guest){
        return iGuestService.addGuest(guest)
                .subscribeOn(Schedulers.io());

    }

    @GetMapping(value = "/deleteGuest/{guestId}")
    Single<String> deleteGuest(@PathVariable("guestId") String guestId){
        return iGuestService.deleteGuest(guestId)
                .subscribeOn(Schedulers.io());
    }
    @GetMapping(value="/displayGuests/{roomId}")
    Single<List<Guest>> displayGuests(@PathVariable("roomId") String roomId){
        return iGuestService.displayGuests(roomId)
                .subscribeOn(Schedulers.io());

    }

    @GetMapping(value = "/guestsResponses/{statementId}")
    Single<List<GuestWithResponseValuesDTO>> getGuestsResponses(@PathVariable("statementId") String statementId){
        return iGuestService.getGuestsResponses(statementId)
                .subscribeOn(Schedulers.io());
    }
}
