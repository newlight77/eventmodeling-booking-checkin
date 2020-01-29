package com.github.newlight77;

public class CheckinRoomHandler {

    private RoomRepository roomRepository;

    public CheckinRoomHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void checkin(CheckinCommand command) {
        RoomCheckinCompleted event = RoomCheckinCompleted.builder()
                .customerName(command.getCustomerName())
                .checkinTime(command.getCheckinTime())
                .roomNumber(command.getRoomNumber())
                .badgeNumber(command.getBadgeNumber())
                .reservationNumber(command.getReservationNumber())
                .build();
        this.roomRepository.save(event);
    }
}
