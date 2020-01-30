package com.github.newlight77;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RoomCheckinCompleted {
    private String customerName;
    private String checkinTime;
    private String roomNumber;
    private String badgeNumber;
    private String reservationNumber;
}
