package com.github.newlight77;

import com.github.newlight77.events.EventBus;
import org.json.simple.JSONObject;

public class RoomWriteRepository {

    private RoomsFileDatabase database;
    public RoomWriteRepository(RoomsFileDatabase database) {
        this.database = database;
    }

    public void save(RoomCheckinCompleted event) {
        JSONObject json = new JSONObject();
        json.put("customerName", event.getCustomerName());
        json.put("badgeNumber", event.getBadgeNumber());
        json.put("checkinTime", event.getCheckinTime());
        json.put("roomNumber", event.getRoomNumber());
        json.put("reservationNumber", event.getReservationNumber());
        database.writeJson(event.getRoomNumber(), json);
    }
}
