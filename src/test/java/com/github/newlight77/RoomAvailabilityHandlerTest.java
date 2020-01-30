package com.github.newlight77;

import com.github.newlight77.model.Rooms;
import com.github.newlight77.specification.Beha4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomAvailabilityHandlerTest {

    @Test
    public void should_return_all_rooms_are_available_when_hotel_just_opened() {
        Rooms rooms = new Rooms(2);
        RoomReadRepository roomReadRepository = new RoomReadRepository(rooms);
        RoomAvailabilityHandler handler = new RoomAvailabilityHandler(roomReadRepository);

        assertThat(handler.availableRooms()).hasSize(2);
    }
}
