package com.github.newlight77.infrastructure.eventstore;

import com.github.newlight77.infrastructure.actors.RoomEventPublisher;
import com.github.newlight77.infrastructure.actors.RoomEventSubscriber;
import com.github.newlight77.model.CheckedInEvent;

import java.util.ArrayList;
import java.util.Collection;

public class EventStore implements IEventStore<CheckedInEvent> {

    private RoomEventPublisher roomEventPublisher;
    private Collection<IEventListener<CheckedInEvent>> listeners = new ArrayList<>();

    public EventStore(RoomEventPublisher roomEventPublisher, RoomEventSubscriber roomEventSubscriber) {
        this.roomEventPublisher = roomEventPublisher;
        roomEventSubscriber.subscribe(e -> {
            CheckedInEvent checkedInEvent = CheckedInEvent.builder()
                    .roomNumber(e.getRoomNumber())
                    .type(e.getType())
                    .customerName(e.getCustomerName())
                    .reservationNumber(e.getReservationNumber())
                    .badgeNumber(e.getBadgeNumber())
                    .checkinTime(e.getCheckinTime())
                    .available(e.isAvailable())
                    .build();
            eventFired(checkedInEvent);
        });
    }

    @Override
    public Collection<IEventListener<CheckedInEvent>> listeners() {
        return listeners;
    }

    @Override
    public void register(IEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void eventFired(CheckedInEvent checkedInEvent) {
        this.store(checkedInEvent);
        this.broadcast(checkedInEvent);
    }

    @Override
    public void store(CheckedInEvent checkedInEvent) {
        roomEventPublisher.publish(checkedInEvent.toCreatedEvent());
    }

    @Override
    public Collection<CheckedInEvent> events(String roomNumber) {
        return new ArrayList<>();
    }

}
