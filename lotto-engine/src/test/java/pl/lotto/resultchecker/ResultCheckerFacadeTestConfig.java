package pl.lotto.resultchecker;

import org.junit.jupiter.api.AfterEach;

class ResultCheckerFacadeTestConfig {
    WinningTicketRepository ticketRepository = new WinningTicketRepositoryTestImpl();

    @AfterEach
    public void clearDatabase() {
        ticketRepository.deleteAll();
    }
}
