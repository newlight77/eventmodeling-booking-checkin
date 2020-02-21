package com.github.newlight77.infrastructure.actors;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.github.newlight77.infrastructure.eventstore.CheckedInEventCreated;
import com.github.newlight77.infrastructure.eventstore.IPublisher;
import eventstore.akka.Settings;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.core.EsException;
import eventstore.core.EventData;
import eventstore.core.WriteEvents;
import eventstore.core.WriteEventsCompleted;
import eventstore.j.EventDataBuilder;
import eventstore.j.SettingsBuilder;
import eventstore.j.WriteEventsBuilder;

import java.net.InetSocketAddress;
import java.util.UUID;

public class RoomEventPublisher implements IPublisher<CheckedInEventCreated> {

    @Override
    public void publish(CheckedInEventCreated roomEvent) {
        final ActorSystem system   = ActorSystem.create();
        final Settings settings = new SettingsBuilder()
                .address(new InetSocketAddress("127.0.0.1", 1113))
                .defaultCredentials("admin", "changeit")
                .build();
        final ActorRef connection  = system.actorOf(ConnectionActor.getProps(settings));
        final ActorRef writeResult = system.actorOf(Props.create(WriteResult.class));

        final EventData event = new EventDataBuilder("room")
                .eventId(UUID.randomUUID())
                .data(roomEvent.toString())
                .metadata("my first event")
                .build();

        final WriteEvents writeEvents = new WriteEventsBuilder("rooms")
                .addEvent(event)
                .expectAnyVersion()
                .build();

        connection.tell(writeEvents, writeResult);

        System.out.println("Event pushed" + writeResult);
    }

    public static class WriteResult extends AbstractActor {

        final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(WriteEventsCompleted.class, m -> {
                        log.info("range: {}, position: {}", m.numbersRange(), m.position());
                        context().system().terminate();
                    })
                    .match(Status.Failure.class, f -> {
                        final EsException exception = (EsException) f.cause();
                        log.error(exception, exception.toString());
                    })
                    .build();
        }

    }
}
