package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.util.List;

import static pl.lotto.resultchecker.WinningTicketMapper.*;

@AllArgsConstructor
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;
    private final NumberChecker numberChecker;
    private final WinningTicketRepository ticketRepository;
    private final static int MINIMAL_AMOUNT_OF_NUMBERS_TO_WIN = 4;

    @Override
    public WinningTicketsDto checkAllWinningTicketsForGivenDrawDate(Instant drawDate) {
        UserTicketsDto userTicketsDto = numberReceiverFacade.usersNumbers(drawDate);
        WinningNumbersDto winningNumbers = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        List<WinningTicket> winningTickets = numberChecker.checkTicketsNumbers(userTicketsDto.tickets(), winningNumbers);
        List<WinningTicket> savedWinningTickets = ticketRepository.saveAll(winningTickets);
        List<WinningTicketDto> winningTicketsDto = toDtoList(savedWinningTickets);
        return WinningTicketsDto.builder()
                .winningTickets(winningTicketsDto)
                .build();
    }

    @Override
    public TicketResultResponseDto isTicketWon(List<Integer> numbers, Instant drawDate) {
        WinningTicket winningTicket = ticketRepository.findByNumbersAndDrawDate(numbers, drawDate).orElse(
                WinningTicket.builder()
                        .numbers(numbers)
                        .drawDate(drawDate)
                        .build());
        return prepareTicketResultResponse(winningTicket);
    }

    @Override
    public TicketResultResponseDto isTicketWon(List<Integer> numbers, Long lotteryNumber) {
        WinningTicket winningTicket = ticketRepository.findByNumbersAndLotteryNumber(numbers, lotteryNumber).orElse(
                WinningTicket.builder()
                        .numbers(numbers)
                        .lotteryNumber(lotteryNumber)
                        .build());
        return prepareTicketResultResponse(winningTicket);
    }

    @Override
    public TicketResultResponseDto isTicketWon(String ticketHash) {
        WinningTicket winningTicket = ticketRepository.findByHash(ticketHash).orElse(
                WinningTicket.builder()
                        .hash(ticketHash)
                        .build());
        return prepareTicketResultResponse(winningTicket);
    }

    private TicketResultResponseDto prepareTicketResultResponse(WinningTicket winningTicket) {
        WinningTicketDto winningTicketDto = toDto(winningTicket);
        if(winningTicket.amountOfCorrectNumbers < MINIMAL_AMOUNT_OF_NUMBERS_TO_WIN){
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


}
