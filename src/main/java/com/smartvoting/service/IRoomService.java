package com.smartvoting.service;

import com.smartvoting.entity.Room;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public interface IRoomService {
    Single<Room> addRoom(Room room);

    Single<Room> getRoomDetails(String roomId);
}
