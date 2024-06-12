package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.List;

public class NumberReceiverFacadeImpl implements NumberReceiverFacade {

    @Override
    public NumberReceiverResultDto inputNumbers(List<Integer> integers) {
        return new NumberReceiverResultDto("a", false);
    }
}
