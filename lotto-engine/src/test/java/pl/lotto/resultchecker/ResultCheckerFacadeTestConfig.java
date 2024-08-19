package pl.lotto.resultchecker;

import org.junit.jupiter.api.AfterEach;

class ResultCheckerFacadeTestConfig {
    protected final WinningTicketRepository ticketRepository = new InMemoryWinningTicketRepository();

    @AfterEach
    public void clearDatabase() {
        ticketRepository.deleteAll();
    }
}
