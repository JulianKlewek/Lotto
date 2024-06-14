package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.*;
import java.util.*;

import static pl.lotto.numberreceiver.TicketMapper.*;
import static pl.lotto.numberreceiver.TicketMapper.toDto;

@Slf4j
@AllArgsConstructor
class NumberReceiverFacadeImpl implements NumberReceiverFacade {

    private final NumberReceiverValidator numberValidator;
    private final HashGenerator hashGenerator;
    private final TicketRepository ticketRepository;
    private final DrawDateGeneratorFacade drawDateGenerator;
    private final Clock clock;

    @Override
    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        NumberValidationResult validationResult = numberValidator.validate(new HashSet<>(numbersFromUser));
        if (!validationResult.isValidationSuccessful()) {
            return new NumberReceiverResultDto(
                    validationResult.validationStatus(),
                    validationResult.errorsList(),
                    null);
        }
        String hash = hashGenerator.getHash();
        DrawDateDto drawDateDto = drawDateGenerator.getNextDrawDate(ZonedDateTime.now(clock));
        Ticket ticket = new Ticket(hash, new HashSet<>(numbersFromUser), drawDateDto.drawDate());
        ticketRepository.save(ticket);
        log.info("Ticket {} registered", hash);
        return new NumberReceiverResultDto(
                validationResult.validationStatus(),
                validationResult.errorsList(),
                toDto(ticket));
    }

    @Override
    public List<TicketDto> usersNumbers(ZonedDateTime drawDate) {
        List<Ticket> ticketsForDate = ticketRepository.findAllByDrawDate(drawDate);
        log.info("Returned {} tickets for date: {}", ticketsForDate.size(), drawDate);
        return toDtoList(ticketsForDate);
    }
}
