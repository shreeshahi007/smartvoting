package com.smartvoting.controller;

import com.smartvoting.entity.Guest;
import com.smartvoting.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    IGuestService iGuestService;

    @PostMapping(value="/addGuest")
    Single<Object> addGuest(@RequestBody Guest guest){
        return iGuestService.addGuest(guest)
                .subscribeOn(Schedulers.io());

    }
}
