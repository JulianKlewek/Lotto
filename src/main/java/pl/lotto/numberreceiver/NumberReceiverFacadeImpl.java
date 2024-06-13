package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Set;

import static pl.lotto.numberreceiver.TicketMapper.toDto;

@AllArgsConstructor
class NumberReceiverFacadeImpl implements NumberReceiverFacade {

    private final NumberReceiverValidator numberValidator;
    private final HashGenerator hashGenerator;
    private final TicketRepository ticketRepository;
    private final DrawDateGeneratorFacade drawDateGenerator;
    private final Clock clock;

    @Override
    public NumberReceiverResultDto inputNumbers(Set<Integer> numbersFromUser) {
        NumberValidationResult validationResult = numberValidator.validate(numbersFromUser);
        if (!validationResult.isValidationSuccessful()) {
            return new NumberReceiverResultDto(
                    validationResult.validationStatus(),
                    validationResult.errorsList(),
                    null);
        }
        String hash = hashGenerator.getHash();
        DrawDateDto drawDateDto = drawDateGenerator.getNextDrawDate(ZonedDateTime.now(clock));
        Ticket ticket = new Ticket(hash, numbersFromUser, drawDateDto.drawDate().toInstant());
        ticketRepository.save(ticket);
        return new NumberReceiverResultDto(
                validationResult.validationStatus(),
                validationResult.errorsList(),
                toDto(ticket));
    }
}
