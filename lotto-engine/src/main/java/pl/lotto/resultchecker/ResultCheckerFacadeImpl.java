package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTickets;
import pl.lotto.resultchecker.dto.*;

import java.time.Instant;
import java.util.List;

import static pl.lotto.resultchecker.WinningTicketMapper.toDtoList;

@Log4j2
@AllArgsConstructor
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersPort winningNumbersPort;
    private final NumberChecker numberChecker;
    private final WinningTicketRepository ticketRepository;
    private final CheckerResponseGenerator checkerResponseGenerator;
    private final NobodyWonTicketGenerator generateDataNobodyWon;

    @Override
    public WinningTickets checkAllWinningTicketsForGivenDrawDate(Instant drawDate) {
        UserTickets userTickets = numberReceiverFacade.usersNumbers(drawDate);
        WinningNumbersResponse winningNumbers = winningNumbersPort.getWinningNumbersForDate(drawDate);
        List<WinningTicket> winningTickets = numberChecker.checkTicketsNumbers(userTickets.tickets(), winningNumbers);
        if (winningTickets.isEmpty()) {
            WinningTicket ticketNobodyWon = generateDataNobodyWon.generateDataNobodyWon(winningNumbers);
            winningTickets.add(ticketNobodyWon);
            log.info("No one won. Adding empty ticket");
        }
        List<WinningTicket> savedWinningTickets = saveWinningTickets(winningTickets, drawDate);
        log.info("Number of winning tickets: [{}] for date: [{}]", savedWinningTickets.size(), drawDate);
        List<WinningTicketPayload> winningTicketsDto = toDtoList(savedWinningTickets);
        return WinningTickets.builder()
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
    public BasicTicketInfoResponse checkGivenNumbersForLottery(List<Integer> userNumbers, Instant drawDate) {
        WinningNumbersResponse winningNumbersDto = winningNumbersPort.getWinningNumbersForDate(drawDate);
        int matchingNumbersAmount = numberChecker.checkTicketNumbers(userNumbers, winningNumbersDto.numbers());
        log.debug("User have [{}] matching numbers. User numbers: [{}], winning numbers: [{}]",
                matchingNumbersAmount, userNumbers, winningNumbersDto.numbers());
        return checkerResponseGenerator.prepareBasicTicketInfoResponse(winningNumbersDto, matchingNumbersAmount);
    }

    @Override
    public BasicTicketInfoResponse checkGivenNumbersForLottery(List<Integer> userNumbers, Long lotteryId) {
        WinningNumbersResponse winningNumbersDto = winningNumbersPort.getWinningNumbersForLotteryNumber(lotteryId);
        int matchingNumbersAmount = numberChecker.checkTicketNumbers(userNumbers, winningNumbersDto.numbers());
        log.debug("User have [{}] matching numbers. User numbers: [{}], winning numbers: [{}]",
                matchingNumbersAmount, userNumbers, winningNumbersDto.numbers());
        return checkerResponseGenerator.prepareBasicTicketInfoResponse(winningNumbersDto, matchingNumbersAmount);
    }

    @Override
    public TicketResultResponse isSpecificTicketWon(String ticketHash) {
        WinningTicket winningTicket = ticketRepository.findByHash(ticketHash)
                .orElse(WinningTicket.builder()
                        .hash(ticketHash)
                        .build());
        return checkerResponseGenerator.prepareTicketResultResponse(winningTicket);
    }

    @Override
    public boolean isSystemGeneratedResults(Instant drawDate) {
        return ticketRepository.existsByDrawDate(drawDate);
    }

    @Override
    public WinningNumbersResults findWinningNumbersForLottery(Instant drawDate) {
        WinningNumbersResponse response = winningNumbersPort.getWinningNumbersForDate(drawDate);
        return WinningNumbersResultMapper.responseToDto(response);
    }

}
