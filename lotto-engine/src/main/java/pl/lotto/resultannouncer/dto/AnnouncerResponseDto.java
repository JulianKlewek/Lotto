package pl.lotto.resultannouncer.dto;

import lombok.Builder;

@Builder
public record AnnouncerResponseDto(
        AnnouncerTicketDto ticket,
        String message) {

}
