package com.github.newlight77;

import org.json.simple.JSONObject;

public class RoomRepository {

    public void save(RoomCheckinCompleted event) {
        JSONObject json = new JSONObject();
        json.put("customerName", event.getCustomerName());
        json.put("badgeNumber", event.getBadgeNumber());
        json.put("checkinTime", event.getCheckinTime());
        json.put("roomNumber", event.getRoomNumber());
        json.put("reservationNumber", event.getReservationNumber());
        new RoomFileBased().writeJson(json);
    }

}
