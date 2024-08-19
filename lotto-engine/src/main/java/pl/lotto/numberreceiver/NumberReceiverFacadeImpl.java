package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.UserTicketsDto;

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
    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        NumberValidationResult validationResult = numberValidator.validate(new HashSet<>(numbersFromUser));
        if (!validationResult.isValidationSuccessful()) {
            log.debug("Input numbers validation failed, numbers: [{}]", numbersFromUser);
            return new NumberReceiverResultDto(
                    validationResult.validationStatus(),
                    validationResult.errorsList(),
                    null);
        }
        String hash = hashGenerator.getHash();
        DrawDateDto drawDateDto = drawDateGenerator.getNextDrawDate(Instant.now(clock));
        Ticket ticket = new Ticket(hash, numbersFromUser, drawDateDto.drawDate());
        Ticket saved = ticketRepository.save(ticket);
        log.info("Ticket [{}] registered", hash);
        return new NumberReceiverResultDto(
                validationResult.validationStatus(),
                validationResult.errorsList(),
                toDto(saved));
    }

    @Override
    public UserTicketsDto usersNumbers(Instant drawDate) {
        List<Ticket> ticketsForDate = ticketRepository.findAllByDrawDate(drawDate);
        log.info("Returned [{}] tickets for date: [{}]", ticketsForDate.size(), drawDate);
        List<TicketDto> ticketsDto = toDtoList(ticketsForDate);
        return UserTicketsDto.builder()
                .tickets(ticketsDto)
                .build();
    }
}
