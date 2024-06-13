package pl.lotto.numberreceiver;

import java.time.Clock;

class NumberReceiverTestConfig {

    TicketRepository ticketRepository = new TicketRepositoryTestImpl();
    Clock clock = Clock.systemUTC();
}
