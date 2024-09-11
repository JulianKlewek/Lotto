package pl.lotto.resultchecker;


import pl.lotto.resultchecker.dto.WinningTicketPayload;

import java.util.List;

public class WinningTicketMapper {

    private WinningTicketMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningTicketPayload toDto(WinningTicket winningTicket) {
        return WinningTicketPayload.builder()
                .userNumbers(winningTicket.userNumbers)
                .hash(winningTicket.hash)
                .lotteryNumber(winningTicket.lotteryNumber)
                .amountOfCorrectNumbers(winningTicket.amountOfCorrectNumbers)
                .drawDate(winningTicket.drawDate)
                .build();
    }

    public static List<WinningTicketPayload> toDtoList(List<WinningTicket> winningTicketList) {
        return winningTicketList.stream()
                .map(WinningTicketMapper::toDto)
                .toList();
    }

}
