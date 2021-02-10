package com.smartvoting.service;

import com.smartvoting.entity.Guest;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public interface IGuestService {
    Single<Object> addGuest(Guest guest);
}
