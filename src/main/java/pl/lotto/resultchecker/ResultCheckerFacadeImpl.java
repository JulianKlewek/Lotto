package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;
    private final NumberChecker numberChecker;
    private final WinningTicketRepository ticketRepository;

    @Override
    public WinningTicketsDto getAllWinningTicketsForGivenDrawDate(Instant drawDate) {
        UserTicketsDto userTicketsDto = numberReceiverFacade.usersNumbers(drawDate);
        WinningNumbersDto winningNumbers = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        List<WinningTicket> winningTickets = numberChecker.checkTicketsNumbers(userTicketsDto.tickets(), winningNumbers);
        List<WinningTicket> savedWinningTickets = ticketRepository.saveAll(winningTickets);
        List<WinningTicketDto> winningTicketsDto = WinningTicketMapper.toDtoList(savedWinningTickets);
        return WinningTicketsDto.builder()
                .winningTickets(winningTicketsDto)
                .build();
    }
}
