package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.UserTicketsDto;

import java.time.Instant;
import java.util.List;

public interface NumberReceiverFacade {

    NumberReceiverResultDto inputNumbers(List<Integer> integers);

    UserTicketsDto usersNumbers(Instant drawDate);
}
