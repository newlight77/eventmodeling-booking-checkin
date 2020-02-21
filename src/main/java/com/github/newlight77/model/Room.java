package com.github.newlight77.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private String roomNumber;
    private String type;
    private String customerName;
    private String badgeNumber;
    private String reservationNumber;
    private String checkinTime;
    private boolean available;
}
