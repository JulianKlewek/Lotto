package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import pl.lotto.resultchecker.ResultStatus;

@AllArgsConstructor
class ResultMessageGenerator {

    private final ResultAnnouncerConfigurable announcerConfigurable;

    public String generateResultMessage(ResultStatus status) {
        return switch (status) {
            case PRIZE_RECEIVED -> announcerConfigurable.getWinMessageReceived();
            case PRIZE_NOT_RECEIVED -> announcerConfigurable.getWinMessageNotReceived();
            case NOT_FOUND -> announcerConfigurable.getLoseMessage();
        };
    }
}
