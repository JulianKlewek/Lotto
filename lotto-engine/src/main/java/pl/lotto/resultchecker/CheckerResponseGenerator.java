package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultchecker.dto.BasicTicketInfoResponse;
import pl.lotto.resultchecker.dto.TicketResultResponse;
import pl.lotto.resultchecker.dto.WinningTicketPayload;

import static pl.lotto.resultchecker.WinningTicketMapper.toDto;


@AllArgsConstructor
class CheckerResponseGenerator {

    private final NumberChecker numberChecker;

    public TicketResultResponse prepareTicketResultResponse(WinningTicket winningTicket) {
        WinningTicketPayload winningTicketPayload = toDto(winningTicket);
        if (!numberChecker.isEnoughMatchingNumbersAmountToWin(winningTicket.amountOfCorrectNumbers)) {
            return TicketResultResponse.builder()
                    .winningTicket(winningTicketPayload)
                    .status(ResultStatus.NOT_FOUND)
                    .build();
        }
        ResultStatus status = winningTicket.collectedReward
                ? ResultStatus.PRIZE_RECEIVED
                : ResultStatus.PRIZE_NOT_RECEIVED;
        return TicketResultResponse.builder()
                .winningTicket(winningTicketPayload)
                .status(status)
                .build();
    }

    public BasicTicketInfoResponse prepareBasicTicketInfoResponse(WinningNumbersResponse winningNumbersDto, int matchingNumbersAmount) {
        boolean won = numberChecker.isEnoughMatchingNumbersAmountToWin(matchingNumbersAmount);
        return BasicTicketInfoResponse.builder()
                .matchingNumbersAmount(matchingNumbersAmount)
                .drawDate(winningNumbersDto.drawDate())
                .lotteryId(winningNumbersDto.lotteryNumber())
                .won(won)
                .build();
    }

}
