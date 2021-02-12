package com.smartvoting.utils;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Room;
import com.smartvoting.repository.RoomRepository;

import java.util.*;

public class Utils {
    public double getMedian(List<Integer> numbers){
        double median = 0;
        int size = numbers.size();
        if(size==0){
            return 0;
        }
        Collections.sort(numbers);
        if(size%2==0){
            int mid = size/2;
            median = (numbers.get(mid)+numbers.get(mid-1))/2;
        } else {
            median = numbers.get(size/2);
        }
        return median;
    }

    public List<Integer> getMode(List<Integer> numbers){
        int size = numbers.size();
        if(size == 0){
            return new ArrayList<>();
        }


        List<Integer> modes = new ArrayList<>();
        HashMap<Integer , Integer> hashMap =  new HashMap<>();
        for (int i : numbers){
            hashMap.put(i , hashMap.getOrDefault(i , 0) + 1);
        }
        int frequency = 0;
        for (Map.Entry<Integer , Integer> entry: hashMap.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();

            frequency = Math.max(value, frequency);
        }
        for (Map.Entry<Integer , Integer> entry : hashMap.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if (frequency == value)
                modes.add(key);
        }
        return modes;
    }

    public boolean validatePassword(GuestDTO guestDTO, RoomRepository roomRepository){
        String roomId = guestDTO.getRoomId();
        String password = guestDTO.getPassword();
        Room room = roomRepository.findById(roomId).get();
        if(room == null){
            return false;
        }
        return password.equals(room.getPassword());

    }
}
