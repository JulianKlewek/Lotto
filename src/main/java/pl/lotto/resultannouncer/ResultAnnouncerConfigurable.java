package pl.lotto.resultannouncer;

public interface ResultAnnouncerConfigurable {

    String getWinMessageReceived();

    void setWinMessageReceived(String message);

    String getWinMessageNotReceived();

    void getWinMessageNotReceived(String message);

    String getLoseMessage();

    void setLoseMessage(String message);
}
