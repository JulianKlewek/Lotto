package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.resultchecker.dto.*;

import java.time.Instant;
import java.util.List;

import static pl.lotto.resultchecker.WinningTicketMapper.toDtoList;

@AllArgsConstructor
@Log4j2
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersPort winningNumbersPort;
    private final NumberChecker numberChecker;
    private final WinningTicketRepository ticketRepository;
    private final CheckerResponseGenerator checkerResponseGenerator;
    private final NobodyWonTicketGenerator generateDataNobodyWon;

    @Override
    public WinningTicketsDto checkAllWinningTicketsForGivenDrawDate(Instant drawDate) {
        UserTicketsDto userTicketsDto = numberReceiverFacade.usersNumbers(drawDate);
        WinningNumbersResponseDto winningNumbers = winningNumbersPort.getWinningNumbersForDate(drawDate);
        List<WinningTicket> winningTickets = numberChecker.checkTicketsNumbers(userTicketsDto.tickets(), winningNumbers);
        if (winningTickets.isEmpty()) {
            WinningTicket ticketNobodyWon = generateDataNobodyWon.generateDataNobodyWon(winningNumbers);
            winningTickets.add(ticketNobodyWon);
            log.info("No one won. Adding empty ticket");
        }
        List<WinningTicket> savedWinningTickets = saveWinningTickets(winningTickets, drawDate);
        List<WinningTicketDto> winningTicketsDto = toDtoList(savedWinningTickets);
        return WinningTicketsDto.builder()
                .winningTickets(winningTicketsDto)
                .build();
    }

    private List<WinningTicket> saveWinningTickets(List<WinningTicket> winningTickets, Instant drawDate) {
        boolean ticketsExists = ticketRepository.existsByDrawDate(drawDate);
        List<WinningTicket> savedWinningTickets;
        if (ticketsExists) {
            savedWinningTickets = ticketRepository.findAllByDrawDate(drawDate);
            log.info("Returned {} winning tickets", savedWinningTickets.size());
            return savedWinningTickets;
        }
        savedWinningTickets = ticketRepository.saveAll(winningTickets);
        log.info("Saved {} winning tickets", savedWinningTickets.size());
        return savedWinningTickets;
    }

    @Override
    public BasicTicketInfoResponseDto checkGivenNumbersForLottery(List<Integer> userNumbers, Instant drawDate) {
        WinningNumbersResponseDto winningNumbersDto = winningNumbersPort.getWinningNumbersForDate(drawDate);
        int matchingNumbersAmount = numberChecker.checkTicketNumbers(userNumbers, winningNumbersDto.numbers());
        return checkerResponseGenerator.prepareBasicTicketInfoResponse(winningNumbersDto, matchingNumbersAmount);
    }

    @Override
    public BasicTicketInfoResponseDto checkGivenNumbersForLottery(List<Integer> userNumbers, Long lotteryId) {
        WinningNumbersResponseDto winningNumbersDto = winningNumbersPort.getWinningNumbersForLotteryNumber(lotteryId);
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

    @Override
    public WinningNumbersResultsDto findWinningNumbersForLottery(Instant drawDate) {
        WinningNumbersResponseDto response = winningNumbersPort.getWinningNumbersForDate(drawDate);
        return WinningNumbersResultMapper.responseToDto(response);
    }

}
