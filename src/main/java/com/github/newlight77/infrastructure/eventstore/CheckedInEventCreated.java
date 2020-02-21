package com.github.newlight77.infrastructure.eventstore;

import com.github.newlight77.model.CheckedInEvent;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
//@RequiredArgsConstructor
public class CheckedInEventCreated implements Serializable {
    private static final long serialVersionUID = 6907239580610148276L;
//    private static final String DELIMITER = "::";

    private String roomNumber;
    private String type;
    private String customerName;
    private String badgeNumber;
    private String reservationNumber;
    private String checkinTime;
    private boolean available;

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
//
//    public String toString() {
//        StringJoiner stringJoiner = new StringJoiner(DELIMITER) //
//                .add(roomNumber) //
//                .add(type) //
//                .add(customerName) //
//                .add(badgeNumber) //
//                .add(reservationNumber) //
//                .add(checkinTime) //
//                .add("" + available);
//        return stringJoiner.toString();
//    }
//
//    public RoomEventCreated deserialize(String tokenized) {
//        StringTokenizer st =  new StringTokenizer(tokenized,  DELIMITER) ;
//        this.roomNumber = st.nextToken() ;
//        this.type = st.nextToken() ;
//        this.customerName = st.nextToken() ;
//        this.badgeNumber = st.nextToken() ;
//        this.reservationNumber = st.nextToken() ;
//        this.checkinTime = st.nextToken() ;
//        this.available = Boolean.parseBoolean(st.nextToken());
//        return this;
//    }
}