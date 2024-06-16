package pl.lotto.resultannouncer;

public interface ResultAnnouncerConfigurable {

    String getWinMessage();
    void setWinMessage(String message);

    String getLoseMessage();
    void setLoseMessage(String message);
}
