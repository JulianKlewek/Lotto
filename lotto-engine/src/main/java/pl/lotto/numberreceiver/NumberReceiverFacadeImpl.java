package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDate;
import pl.lotto.numberreceiver.dto.NumberReceiverResult;
import pl.lotto.numberreceiver.dto.TicketPayload;
import pl.lotto.numberreceiver.dto.UserTickets;

import java.time.Clock;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import static pl.lotto.numberreceiver.TicketMapper.toDto;
import static pl.lotto.numberreceiver.TicketMapper.toDtoList;

@Log4j2
@AllArgsConstructor
class NumberReceiverFacadeImpl implements NumberReceiverFacade {

    private final NumberReceiverValidator numberValidator;
    private final HashGenerator hashGenerator;
    private final TicketRepository ticketRepository;
    private final DrawDateGeneratorFacade drawDateGenerator;
    private final Clock clock;

    @Override
    public NumberReceiverResult inputNumbers(List<Integer> numbersFromUser) {
        NumberValidationResult validationResult = numberValidator.validate(new HashSet<>(numbersFromUser));
        if (!validationResult.isValidationSuccessful()) {
            log.debug("Input numbers validation failed, numbers: [{}]", numbersFromUser);
            return new NumberReceiverResult(
                    validationResult.validationStatus(),
                    validationResult.errorsList(),
                    null);
        }
        String hash = hashGenerator.getHash();
        DrawDate drawDate = drawDateGenerator.getNextDrawDate(Instant.now(clock));
        pl.lotto.numberreceiver.Ticket ticket = new pl.lotto.numberreceiver.Ticket(hash, numbersFromUser, drawDate.drawDate());
        pl.lotto.numberreceiver.Ticket saved = ticketRepository.save(ticket);
        log.info("Ticket [{}] registered", hash);
        return new NumberReceiverResult(
                validationResult.validationStatus(),
                validationResult.errorsList(),
                toDto(saved));
    }

    @Override
    public UserTickets usersNumbers(Instant drawDate) {
        List<pl.lotto.numberreceiver.Ticket> ticketsForDate = ticketRepository.findAllByDrawDate(drawDate);
        log.info("Returned [{}] tickets for date: [{}]", ticketsForDate.size(), drawDate);
        List<TicketPayload> tickets = toDtoList(ticketsForDate);
        return UserTickets.builder()
                .tickets(tickets)
                .build();
    }
}
