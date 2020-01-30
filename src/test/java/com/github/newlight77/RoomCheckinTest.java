package com.github.newlight77;

import com.sck2.beha4j.Beha4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

public class RoomCheckinTest {

    @Test
    public void should_process_room_checkin_event() {

        final CheckinCommand.CheckinCommandBuilder builder = CheckinCommand.builder();
        final RoomRepository repo = new RoomRepository();
        final CheckinRoomHandler handler = new CheckinRoomHandler(repo);

        Beha4j
            .scenario("should_process_room_checkin_event")
            .given("there is a list with 3 string", name -> {
                builder
                        .customerName("Jane Jackson")
                        .checkinTime(LocalDateTime.of(2020, 1, 30, 10, 11, 21))
                        .badgeNumber("12345")
                        .reservationNumber("1234556")
                        .roomNumber("12312");
            })
            .when("the 2 lists are merged", name -> {
                handler.checkin(builder.build());
            })
            .then("this resulting list has 5 strings", name -> {
                String expectedJson = "{\"checkinTime\":\"2020-01-30T10:11:21\",\"roomNumber\":\"12312\",\"reservationNumber\":\"1234556\",\"badgeNumber\":\"12345\",\"customerName\":\"Jane Jackson\"}";
                Assertions.assertThat(repo.getAll()).isEqualTo(expectedJson);
            });
    }
}
