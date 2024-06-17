package pl.lotto.resultchecker;

import org.junit.jupiter.api.AfterEach;

class ResultCheckerFacadeTestConfig {
    WinnerTicketRepositoryTestImpl ticketRepository = new WinnerTicketRepositoryTestImpl();

    @AfterEach
    public void clearDatabase() {
        ticketRepository.database.clear();
    }
}
