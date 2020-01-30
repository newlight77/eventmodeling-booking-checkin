package com.github.newlight77;

import com.github.newlight77.events.EventBus;
import com.github.newlight77.model.Room;
import com.github.newlight77.model.Rooms;
import com.github.newlight77.specification.Beha4j;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomAvailabilityHandlerTest {

    private Rooms rooms = new Rooms(4);
    final RoomsFileDatabase database = new RoomsFileDatabase(rooms);
    final RoomReadRepository readRepository = new RoomReadRepository(database);
    final RoomAvailabilityHandler handler = new RoomAvailabilityHandler(readRepository);
    final RoomWriteRepository writeRepository = new RoomWriteRepository(database);
    final CheckinRoomHandler checkinHandler = new CheckinRoomHandler(writeRepository);

    @Test
    public void should_return_all_rooms_are_available_when_hotel_just_opened() {
        assertThat(handler.availableRooms()).hasSize(4);
    }

    @Test
    public void should_return_no_available_rooms_when_hotel_just_opened() {
        for (Room room : rooms.allRooms()) {
            room.setOccupied(true);
        }
        assertThat(handler.availableRooms()).isEmpty();
    }

    @Test
    public void should_upadate_availability_when_checkin_is_done() {
        final CheckinCommand.CheckinCommandBuilder builder = CheckinCommand.builder();
        Beha4j
                .scenario("should_upadate_availability_when_checkin_is_done")
                .given("4 rooms available", name -> {
                    builder
                            .customerName("Jane Jackson")
                            .checkinTime(LocalDateTime.of(2020, 1, 30, 10, 11, 21))
                            .badgeNumber("12345")
                            .reservationNumber("1234556")
                            .roomNumber("2");
                })
                .when("a checkin is done", name -> {
                    checkinHandler.checkin(builder.build());
                })
                .then("there are only 3 rooms available", name -> {
                    assertThat(handler.availableRooms()).hasSize(3);
                })
                .print();
    }
}
