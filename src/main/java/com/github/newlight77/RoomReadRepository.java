package com.github.newlight77;

public class RoomReadRepository {

    public String getCheckin(String roomNumber) {
        return new RoomFileBased().readJson(roomNumber).toString();
    }

}
