package pl.lotto.resultchecker;


import pl.lotto.resultchecker.dto.WinningTicketDto;

import java.util.List;

public class WinningTicketMapper {

    private WinningTicketMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningTicketDto toDto(WinningTicket winningTicket) {
        return WinningTicketDto.builder()
                .numbers(winningTicket.numbers)
                .hash(winningTicket.hash)
                .lotteryNumber(winningTicket.lotteryNumber)
                .amountOfCorrectNumbers(winningTicket.amountOfCorrectNumbers)
                .drawDate(winningTicket.drawDate)
                .build();
    }

    public static List<WinningTicketDto> toDtoList(List<WinningTicket> winningTicketList) {
        return winningTicketList.stream()
                .map(WinningTicketMapper::toDto)
                .toList();
    }

}
