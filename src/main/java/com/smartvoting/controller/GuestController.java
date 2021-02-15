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
    private static final String[] avatars = {
            "https://imgur.com/v9dDexB.png",
            "https://imgur.com/b36Pd3q.png",
            "https://imgur.com/PBQRbcv.png",
            "https://imgur.com/2BXhwbB.png",
            "https://imgur.com/3uefIAv.png",
            "https://imgur.com/6y37gQ1.png",
            "https://imgur.com/Nhgocus.png",
            "https://imgur.com/pNbYDLk.png",
            "https://imgur.com/Ka2JeTn.png",
            "https://imgur.com/FsqVqF4.png",
            "https://imgur.com/ehqxeo8.png",
            "https://imgur.com/yS7XTC6.png",
            "https://imgur.com/KGl0vzD.png",
            "https://imgur.com/c9nM3lI.png",
            "https://imgur.com/7k0o6G0.png",
            "https://imgur.com/38OcWn1.png",
            "https://imgur.com/3u4slwM.png",
            "https://imgur.com/iYeujzC.png",
            "https://imgur.com/r31dhsc.png",
            "https://imgur.com/7a1mzuB.png"
    };

    public static int generateRandom(int min, int max) {
        max = avatars.length;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        min++;
        if (min >= max) {
            min = max % min;
        }
        return randomNum;
    }

    @Autowired
    IGuestService iGuestService;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping(value = "/addGuest")
    Single<Guest> addGuest(@RequestBody GuestDTO guest) {
        guest.setPhotoUrl(avatars[generateRandom(0, avatars.length)]);
        return iGuestService.addGuest(guest)
                .subscribeOn(Schedulers.io());

    }

    @GetMapping(value = "/deleteGuest/{guestId}")
    Single<String> deleteGuest(@PathVariable("guestId") String guestId) {
        return iGuestService.deleteGuest(guestId)
                .subscribeOn(Schedulers.io());
    }

    @GetMapping(value = "/displayGuests/{roomId}")
    Single<List<GuestDTO>> displayGuests(@PathVariable("roomId") String roomId) {
        return iGuestService.displayGuests(roomId)
                .subscribeOn(Schedulers.io());

    }

    @GetMapping(value = "/guestsResponses/{statementId}")
    Single<List<GuestWithResponseValuesDTO>> getGuestsResponses(@PathVariable("statementId") String statementId) {
        return iGuestService.getGuestsResponses(statementId)
                .subscribeOn(Schedulers.io());
    }

    @PostMapping(value = "/addGuestAsAdmin")
    Single<Guest> addGuestAsAdmin(@RequestBody GuestDTO guestDTO) {
        Utils utils = new Utils();
        boolean isAdminResult = utils.validatePassword(guestDTO, roomRepository);
        if (isAdminResult) {
            guestDTO.setPhotoUrl(avatars[generateRandom(0, avatars.length)]);
            return iGuestService.addGuest(guestDTO)
                    .subscribeOn(Schedulers.io());
        }
        return null;
    }
}
