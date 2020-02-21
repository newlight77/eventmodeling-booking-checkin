package com.github.newlight77.uc.checkin;

import com.github.newlight77.infrastructure.database.HotelDatabase;
import com.github.newlight77.infrastructure.eventstore.*;
import com.github.newlight77.infrastructure.adapters.RoomReadRepository;
import com.github.newlight77.infrastructure.adapters.RoomWriteRepository;
import com.github.newlight77.model.CheckedInEvent;
import com.github.newlight77.specification.Beha4j;
import com.github.newlight77.uc.availability.RoomAvailabilityProcessor;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

public class RoomCheckinTest {
        @Test
        public void should_process_room_checkin_event() {

        final IEventStore<CheckedInEvent> eventStoreFile = new EventStoreFile(new EventStoreFileConfig());
        final IEventStore<CheckedInEvent> eventStoreProxy = new EventStoreProxy(eventStoreFile);

        // using an eventStore proxy
        HotelDatabase database = new HotelDatabase();
        final RoomReadRepository roomReadRepository = new RoomReadRepository(eventStoreProxy, database);
        final RoomWriteRepository roomWriteRepository = new RoomWriteRepository(eventStoreProxy, database);

        final CheckinCommand.CheckinCommandBuilder builder = CheckinCommand.builder();
        final CheckinRoomHandler handler = new CheckinRoomHandler(roomWriteRepository, roomReadRepository);

        final RoomAvailabilityProcessor availabilityProcessor =
                new RoomAvailabilityProcessor(roomReadRepository, roomWriteRepository, eventStoreProxy);

        Beha4j
            .scenario("Room checkin")
            .given("there is a totla of 5 rooms available", name -> {
                database.total(5);
            })
            .given("a customer named Jane Jackson", name -> {
                builder
                        .customerName("Jane Jackson")
                        .checkinTime(LocalDateTime.of(2020, 1, 30, 10, 11, 21))
                        .badgeNumber("12345")
                        .reservationNumber("1234556")
                        .roomNumber("2");
            })
            .when("Jane Jackson books a room in our hotel", name -> {
                handler.checkin(builder.build());
            })
            .then("then one room is not more available, only 4 out of 5 are available", name -> {
                Assertions.assertThat(roomReadRepository.availableRooms().size()).isEqualTo(4);
            })
            .then("the room is checked in and is no more available", name -> {
                Assertions.assertThat(roomReadRepository.projectRoom("2")).isNotNull();
                Assertions.assertThat(roomReadRepository.projectRoom("2").isAvailable()).isFalse();
            })
            .then("the room is checked in with a badge number and reservation number", name -> {
                Assertions.assertThat(roomReadRepository.projectRoom("2").getCustomerName()).isEqualTo("Jane Jackson");
                Assertions.assertThat(roomReadRepository.projectRoom("2").getBadgeNumber()).isEqualTo("12345");
                Assertions.assertThat(roomReadRepository.projectRoom("2").getReservationNumber()).isEqualTo("1234556");
                Assertions.assertThat(roomReadRepository.projectRoom("2").getCheckinTime()).isEqualTo("2020-01-30T10:11:21");
            })
        .print();
    }

}
