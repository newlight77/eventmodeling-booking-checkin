package com.github.newlight77.uc.availability;

import com.github.newlight77.infrastructure.actors.RoomEventPublisher;
import com.github.newlight77.infrastructure.actors.RoomEventSubscriber;
import com.github.newlight77.infrastructure.database.HotelDatabase;
import com.github.newlight77.infrastructure.eventstore.EventStore;
import com.github.newlight77.infrastructure.adapters.RoomReadRepository;
import com.github.newlight77.infrastructure.adapters.RoomWriteRepository;
import com.github.newlight77.model.Room;
import com.github.newlight77.specification.Beha4j;
import com.github.newlight77.uc.checkin.CheckinCommand;
import com.github.newlight77.uc.checkin.CheckinRoomHandler;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

public class RoomAvailabilityProcessorTest {

    final RoomEventPublisher roomRoomEventPublisher = new RoomEventPublisher();
    final RoomEventSubscriber roomRoomEventSubscriber = new RoomEventSubscriber();
    final EventStore eventStore = new EventStore(roomRoomEventPublisher, roomRoomEventSubscriber);
    final HotelDatabase database = new HotelDatabase(4);

    final RoomReadRepository readRepository = new RoomReadRepository(eventStore, database);
    final RoomWriteRepository writeRepository = new RoomWriteRepository(eventStore, database);
    final RoomAvailabilityProcessor availabilityHandler = new RoomAvailabilityProcessor(readRepository, writeRepository, eventStore);
    final CheckinRoomHandler checkinHandler = new CheckinRoomHandler(writeRepository, readRepository);

//    public static void main(String[] args) {
//        RoomAvailabilityProcessorTest test = new RoomAvailabilityProcessorTest();
//        test.should_return_all_rooms_are_available_when_hotel_just_opened();
//        test.should_return_no_available_rooms_when_hotel_just_opened();
//        test.should_upadate_availability_when_checkin_is_done();
//    }

    @Test
    public void should_return_all_rooms_are_available_when_hotel_just_opened() {
        Assertions.assertThat(availabilityHandler.availableRooms()).hasSize(4);
    }

    @Test
    public void should_return_no_available_rooms_when_hotel_just_opened() {
        for (Room room : database.getRooms()) {
            room.setAvailable(false);
        }
        Assertions.assertThat(availabilityHandler.availableRooms()).isEmpty();
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
                    Assertions.assertThat(availabilityHandler.availableRooms()).hasSize(3);
                })
                .print();
    }
}
