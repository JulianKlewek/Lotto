package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import pl.lotto.resultchecker.dto.BasicTicketInfoResponseDto;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;

import static pl.lotto.resultchecker.WinningTicketMapper.toDto;


@AllArgsConstructor
class CheckerResponseGenerator {

    private final NumberChecker numberChecker;

    public TicketResultResponseDto prepareTicketResultResponse(WinningTicket winningTicket) {
        WinningTicketDto winningTicketDto = toDto(winningTicket);
        if (!numberChecker.isEnoughMatchingNumbersAmountToWin(winningTicket.amountOfCorrectNumbers)) {
            return TicketResultResponseDto.builder()
                    .winningTicket(winningTicketDto)
                    .status(ResultStatus.NOT_FOUND)
                    .build();
        }
        ResultStatus status = winningTicket.collectedReward
                ? ResultStatus.PRIZE_RECEIVED
                : ResultStatus.PRIZE_NOT_RECEIVED;
        return TicketResultResponseDto.builder()
                .winningTicket(winningTicketDto)
                .status(status)
                .build();
    }

    public BasicTicketInfoResponseDto prepareBasicTicketInfoResponse(WinningNumbersResponseDto winningNumbersDto, int matchingNumbersAmount) {
        boolean won = numberChecker.isEnoughMatchingNumbersAmountToWin(matchingNumbersAmount);
        return BasicTicketInfoResponseDto.builder()
                .matchingNumbersAmount(matchingNumbersAmount)
                .drawDate(winningNumbersDto.drawDate())
                .lotteryId(winningNumbersDto.lotteryNumber())
                .won(won)
                .build();
    }

}
