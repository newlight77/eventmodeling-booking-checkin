package com.github.newlight77;

import com.github.newlight77.events.EventBus;
import com.github.newlight77.specification.Beha4j;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomCheckinTest {

    @Test
    public void should_process_room_checkin_event() {

        final RoomsFileDatabase database = new RoomsFileDatabase();
        final CheckinCommand.CheckinCommandBuilder builder = CheckinCommand.builder();
        final RoomWriteRepository writeRepository = new RoomWriteRepository(database);
        final RoomReadRepository readRepository = new RoomReadRepository(database);
        final CheckinRoomHandler handler = new CheckinRoomHandler(writeRepository);

        Beha4j
            .scenario("should_process_room_checkin_event")
            .given("a custommer named Jane Jackson", name -> {
                builder
                        .customerName("Jane Jackson")
                        .checkinTime(LocalDateTime.of(2020, 1, 30, 10, 11, 21))
                        .badgeNumber("12345")
                        .reservationNumber("1234556")
                        .roomNumber("12312");
            })
            .when("Jane Jackson books a room in our hotel", name -> {
                handler.checkin(builder.build());
            })
            .then("a booking event is created", name -> {
                String event = readRepository.getCheckin("12312");
                assertThat(event).contains("\"checkinTime\":\"2020-01-30T10:11:21\"");
                assertThat(event).contains("\"roomNumber\":\"12312\"");
                assertThat(event).contains("\"reservationNumber\":\"1234556\"");
                assertThat(event).contains("\"badgeNumber\":\"12345\"");
                assertThat(event).contains("\"customerName\":\"Jane Jackson\"");
            })
        .print();
    }
}
