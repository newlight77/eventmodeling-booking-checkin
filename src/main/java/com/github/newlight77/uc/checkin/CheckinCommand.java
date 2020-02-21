package com.github.newlight77.uc.checkin;

import com.github.newlight77.model.CheckedInEvent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CheckinCommand {
    private String customerName;
    private LocalDateTime checkinTime;
    private String roomNumber;
    private String badgeNumber;
    private String reservationNumber;

    public CheckedInEvent toEvent() {
        return CheckedInEvent.builder()
                .roomNumber(this.getRoomNumber())
                .customerName(this.getCustomerName())
                .checkinTime(this.getCheckinTime().toString())
                .roomNumber(this.getRoomNumber())
                .badgeNumber(this.getBadgeNumber())
                .reservationNumber(this.getReservationNumber())
                .build();
    }
}
