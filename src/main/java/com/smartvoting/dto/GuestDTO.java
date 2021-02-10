package com.smartvoting.dto;

import com.smartvoting.entity.Room;
import lombok.Data;

@Data
public class GuestDTO {

    String roomId;
    String guestName;
    boolean isAdmin;
    String photoUrl;
}
