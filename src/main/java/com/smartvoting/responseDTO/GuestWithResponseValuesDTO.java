package com.smartvoting.responseDTO;

import lombok.Data;
import org.springframework.stereotype.Component;

//@Component
@Data
public class GuestWithResponseValuesDTO {
    String guestName;
    String guestId;
    String photoUrl;
    Integer responseValue;
}

