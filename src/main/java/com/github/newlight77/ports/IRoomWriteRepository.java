package com.github.newlight77.ports;

import com.github.newlight77.model.CheckedInEvent;

public interface IRoomWriteRepository<CheckedInEvent> {
    void checkin(CheckedInEvent checkedInEvent);
    void roomAvailable(String roomNumber, boolean available);
}
