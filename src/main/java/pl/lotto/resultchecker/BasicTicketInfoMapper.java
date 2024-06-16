package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.BasicTicketInfoResponseDto;

class BasicTicketInfoMapper {
    private BasicTicketInfoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BasicTicketInfoResponseDto toDto(WinningTicket winningTicket) {
        return BasicTicketInfoResponseDto.builder()
                .lotteryId(winningTicket.lotteryNumber)
                .won(true)
                .drawDate(winningTicket.drawDate)
                .matchingNumbersAmount(winningTicket.amountOfCorrectNumbers)
                .build();
    }

}
