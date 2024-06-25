package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;

class AnnouncerTicketMapper {

    private AnnouncerTicketMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnouncerTicketDto toDto(WinningTicketDto ticket) {
        return AnnouncerTicketDto.builder()
                .hash(ticket.hash())
                .lotteryNumber(ticket.lotteryNumber())
                .drawDate(ticket.drawDate())
                .userNumbers(ticket.userNumbers())
                .amountOfCorrectNumbers(ticket.amountOfCorrectNumbers())
                .build();
    }
}
