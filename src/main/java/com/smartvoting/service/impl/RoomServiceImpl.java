package com.smartvoting.service.impl;

import com.smartvoting.entity.Room;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class RoomServiceImpl implements IRoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Single<Room> addRoom(Room room) {
        return Single.create(
             singleSubscriber -> {
                 Room addedRoom = roomRepository.save(room);
                 singleSubscriber.onSuccess(addedRoom);
             }
        );
    }

    @Override
    public Single<Room> getRoomDetails(String roomId) {
        return Single.create(
                singleSubscriber -> {
                    try {
                        Room roomDetail = roomRepository.findById(roomId).get();
                        singleSubscriber.onSuccess(roomDetail);
                    } catch (Exception e) {
                        singleSubscriber.onError(e);
                    }
                }
        );
    }
}
