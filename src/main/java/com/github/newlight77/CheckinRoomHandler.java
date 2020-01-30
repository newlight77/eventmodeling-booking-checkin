package com.github.newlight77;

public class CheckinRoomHandler {

    private RoomWriteRepository roomWriteRepository;

    public CheckinRoomHandler(RoomWriteRepository roomRepository) {
        this.roomWriteRepository = roomRepository;
    }

    public void checkin(CheckinCommand command) {
        RoomCheckinCompleted event = RoomCheckinCompleted.builder()
                .customerName(command.getCustomerName())
                .checkinTime(command.getCheckinTime().toString())
                .roomNumber(command.getRoomNumber())
                .badgeNumber(command.getBadgeNumber())
                .reservationNumber(command.getReservationNumber())
                .build();
        this.roomWriteRepository.save(event);
    }
}
