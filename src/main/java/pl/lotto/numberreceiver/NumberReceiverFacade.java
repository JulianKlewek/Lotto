package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.List;

public interface NumberReceiverFacade {

    NumberReceiverResultDto inputNumbers(List<Integer> integers);
}
