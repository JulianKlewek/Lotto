package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import pl.lotto.resultchecker.ResultStatus;

@AllArgsConstructor
class ResultMessageGenerator {

    private final ResultAnnouncerConfigurable announcerConfigurable;

    public String generateResultMessage(ResultStatus status) {
        String message = "";
        switch (status) {
            case PRIZE_RECEIVED:
                message = announcerConfigurable.getWinMessageReceived();
                break;
            case PRIZE_NOT_RECEIVED:
                message = announcerConfigurable.getWinMessageNotReceived();
                break;
            case NOT_FOUND:
                message = announcerConfigurable.getLoseMessage();
                break;
        }
        return message;
    }
}
