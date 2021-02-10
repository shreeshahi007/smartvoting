package com.smartvoting.service.impl;

import com.smartvoting.entity.Guest;
import com.smartvoting.repository.GuestRepository;
import com.smartvoting.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class GuestServiceImpl implements IGuestService {

    @Autowired
    GuestRepository guestRepository;

    @Override
    public Single<Object> addGuest(Guest guest) {
        return Single.create(
                singleSubscriber -> {
                    Guest addedGuest = guestRepository.save(guest);
                    singleSubscriber.onSuccess(addedGuest);
                }
        );
    }
}
