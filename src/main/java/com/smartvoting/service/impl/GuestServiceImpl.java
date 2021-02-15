package com.smartvoting.service.impl;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Guest;
import com.smartvoting.entity.Responses;
import com.smartvoting.entity.Room;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.GuestRepository;
import com.smartvoting.repository.ResponsesRepository;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.responseDTO.GuestWithResponseValuesDTO;
import com.smartvoting.service.IGuestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServiceImpl implements IGuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    StatementRepository statementRepository;

    @Autowired
    ResponsesRepository responsesRepository;

    @Override
    public Single<Guest> addGuest(GuestDTO guestDTO) {
        return Single.create(
                singleSubscriber -> {
                    Guest addedGuest = guestRepository.save(toGuest(guestDTO));
                    singleSubscriber.onSuccess(addedGuest);
                }
        );
    }

    @Override
    public Guest toGuest(GuestDTO guestDTO) {
        Guest addedGuest = new Guest();
        BeanUtils.copyProperties(guestDTO, addedGuest);
        Room room = roomRepository.findById(guestDTO.getRoomId()).get();
        addedGuest.setRoom(room);
        return addedGuest;
    }

    @Override
    public Single<String> deleteGuest(String guestId) {
        return Single.create(
                singleSubscriber -> {
                    try {
                        guestRepository.deleteById(guestId);
                        singleSubscriber.onSuccess("Delete the guest");
                    } catch (Exception e) {
                        singleSubscriber.onError(e);
                    }
                }
        );
    }

    @Override
    public Single<List<GuestDTO>> displayGuests(String roomId) {
        return Single.create(
                singleSubscriber -> {
                    try {
                        singleSubscriber.onSuccess(displayGuestsHelper(roomId));
                    } catch (Exception e) {
                        singleSubscriber.onError(e);
                    }
                }
        );
    }

    @Override
    public List<GuestDTO> displayGuestsHelper(String roomId) {
//        List<GuestDTO> guests = new ArrayList<>();
        List<Guest> guests1 = guestRepository.findByRoomId(roomId);
        List<GuestDTO> guests = guests1.stream().map(guest -> {
            GuestDTO guestDTO = new GuestDTO();
            guestDTO.setGuestName(guest.getGuestName());
            guestDTO.setRoomId(roomId);
            guestDTO.setPhotoUrl(guest.getPhotoUrl());
            guestDTO.setAdmin(guest.isAdmin());
            System.out.println(guest);
            System.out.println(guestDTO);
            return guestDTO;
        }).collect(Collectors.toList());
        return guests;
    }

    @Override
    public Single<List<GuestWithResponseValuesDTO>> getGuestsResponses(String statementId) {
        return Single.create(
                singleSubscriber -> {
                    try {
                        List<GuestWithResponseValuesDTO> guestWithResponseValues = getGuestsResponsesHelper(statementId);
                        singleSubscriber.onSuccess(guestWithResponseValues);
                    } catch (Exception e) {
                        singleSubscriber.onError(e);
                    }
                }
        );
    }

    @Override
    public List<GuestWithResponseValuesDTO> getGuestsResponsesHelper(String statementId) {
//        System.out.println("Inside getGuestsResponsesHelper");
//        System.out.println(statementId);
        if(statementId.equals("undefined")){
            return new ArrayList<>();
        }
        Statement statement = statementRepository.findById(statementId).get();
        List<Guest> guests = guestRepository.findByRoomId(statement.getRoomId());
        List<GuestWithResponseValuesDTO> guestWithResponseValues = new ArrayList<>();
        for (Guest guest : guests) {
            GuestWithResponseValuesDTO guestWithResponseValues1 = new GuestWithResponseValuesDTO();
            BeanUtils.copyProperties(guest, guestWithResponseValues1);
            Responses responses;
            try {
                responses = responsesRepository.findByStatementIdAndGuestId(statementId, guest.getGuestId());
            } catch (Exception e) {
                responses = null;
                e.printStackTrace();
            }
            guestWithResponseValues1.setAdmin(guest.isAdmin());
            if (responses == null) {
                guestWithResponseValues1.setResponseValue(0);
            } else {
                guestWithResponseValues1.setResponseValue(responses.getResponseValue());
            }
            guestWithResponseValues.add(guestWithResponseValues1);

        }
//        System.out.println("Sent the response from getGuestsResponsesHelper");
        return guestWithResponseValues;
    }
}
