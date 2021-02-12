package com.smartvoting.controller;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Guest;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.responseDTO.GuestWithResponseValuesDTO;
import com.smartvoting.service.IGuestService;
import com.smartvoting.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin
@RestController
@RequestMapping("/guest")
public class GuestController {
     private static String[] avatars={"https://imgur.com/v9dDexB",
    "https://imgur.com/b36Pd3q",
    "https://imgur.com/PBQRbcv",
    "https://imgur.com/2BXhwbB",
    "https://imgur.com/3uefIAv",
    "https://imgur.com/6y37gQ1",
    "https://imgur.com/Nhgocus",
    "https://imgur.com/pNbYDLk",
    "https://imgur.com/Ka2JeTn",
    "https://imgur.com/FsqVqF4",
    "https://imgur.com/ehqxeo8",
    "https://imgur.com/yS7XTC6",
    "https://imgur.com/KGl0vzD",
    "https://imgur.com/c9nM3lI",
    "https://imgur.com/7k0o6G0",
    "https://imgur.com/38OcWn1",
    "https://imgur.com/3u4slwM",
    "https://imgur.com/iYeujzC",
    "https://imgur.com/r31dhsc",
    "https://imgur.com/7a1mzuB"
    };
public static int generateRandom(int min,int max) {
    max=avatars.length;
    int randomNum = ThreadLocalRandom.current().nextInt(min, max);
    min++;
    if(min==max)
    {
        min=max % min;
    }
    return randomNum;
}

    @Autowired
    IGuestService iGuestService;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping(value="/addGuest")
    Single<Guest> addGuest(@RequestBody GuestDTO guest){
        guest.setPhotoUrl(avatars[generateRandom(0,avatars.length)]);
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

    @PostMapping(value = "/addGuestAsAdmin")
    Single<Guest> addGuestAsAdmin(@RequestBody GuestDTO guestDTO){
        Utils utils = new Utils();
        boolean isAdminResult =  utils.validatePassword(guestDTO, roomRepository);
        if(isAdminResult){
            return iGuestService.addGuest(guestDTO)
            .subscribeOn(Schedulers.io());
        }
        return null;
    }
}
