package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.Set;

public interface NumberReceiverFacade {

    NumberReceiverResultDto inputNumbers(Set<Integer> integers);
}
