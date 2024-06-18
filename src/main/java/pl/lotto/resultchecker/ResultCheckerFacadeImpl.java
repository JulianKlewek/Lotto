package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;
import pl.lotto.resultchecker.dto.BasicTicketInfoResponseDto;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.util.List;

import static pl.lotto.resultchecker.WinningTicketMapper.toDtoList;

@AllArgsConstructor
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;
    private final NumberChecker numberChecker;
    private final WinningTicketRepository ticketRepository;
    private final CheckerResponseGenerator checkerResponseGenerator;

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
    public BasicTicketInfoResponseDto checkGivenNumbersForLottery(List<Integer> userNumbers, Instant drawDate) {
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        int matchingNumbersAmount = numberChecker.checkTicketNumbers(userNumbers, winningNumbersDto.numbers());
        return checkerResponseGenerator.prepareBasicTicketInfoResponse(winningNumbersDto, matchingNumbersAmount);
    }

    @Override
    public BasicTicketInfoResponseDto checkGivenNumbersForLottery(List<Integer> userNumbers, Long lotteryNumber) {
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.getWinningNumbersForLotteryNumber(lotteryNumber);
        int matchingNumbersAmount = numberChecker.checkTicketNumbers(userNumbers, winningNumbersDto.numbers());
        return checkerResponseGenerator.prepareBasicTicketInfoResponse(winningNumbersDto, matchingNumbersAmount);
    }

    @Override
    public TicketResultResponseDto isSpecificTicketWon(String ticketHash) {
        WinningTicket winningTicket = ticketRepository.findByHash(ticketHash).orElse(
                WinningTicket.builder()
                        .hash(ticketHash)
                        .build());
        return checkerResponseGenerator.prepareTicketResultResponse(winningTicket);
    }

    @Override
    public boolean isSystemGeneratedResults(Instant drawDate) {
        return ticketRepository.existsByDrawDate(drawDate);
    }

}
