package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.LotteryTicketResponse;
import pl.lotto.numberreceiver.dto.UserTickets;

import java.time.Instant;
import java.util.List;

public interface NumberReceiverFacade {

    LotteryTicketResponse inputNumbers(List<Integer> integers);

    UserTickets usersNumbers(Instant drawDate);
}
