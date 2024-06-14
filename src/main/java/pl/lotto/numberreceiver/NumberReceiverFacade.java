package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberReceiverFacade {

    NumberReceiverResultDto inputNumbers(List<Integer> integers);

    List<TicketDto> usersNumbers(LocalDateTime drawDate);
}
