package com.github.newlight77.ports;

import java.util.Collection;

public interface IRoomReadRepository<Room> {
    Room projectRoom(String roomNumber);
    boolean isAvailable(String roomNumber);
    Collection<Room> availableRooms();
}
