package com.smartvoting.service;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Guest;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.List;

@Service
public interface IGuestService {
    Single<Guest> addGuest(GuestDTO guest);

    Guest toGuest(GuestDTO guestDTO);

    Single<String> deleteGuest(String guestId);

    Single<List<Guest>> displayGuests(String roomId);
}
