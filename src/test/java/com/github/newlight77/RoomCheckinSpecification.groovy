package com.github.newlight77

import java.time.LocalDateTime

class RoomCheckinSpecification {

    def "Should verify notify was called"() {
        given:
        def command = CheckinCommand.builder()
                .customerName("Jane Jackson")
                .checkinTime(LocalDateTime.now())
                .badgeNumber("12345")
                .reservationNumber("1234556")
                .roomNumber("12312")
                .build();

        when:
        new CheckinRoomHandler(new RoomRepository()).checkin(command)

        then:
        1 * notifier.notify('foo')
        Assert.that(item.activated ==false);
    }
}
