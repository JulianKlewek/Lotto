package pl.lotto.resultannouncer;

class ResultAnnouncerPropertyConfigTest implements ResultAnnouncerConfigurable {
    private String winMessageReceived;
    private String winMessageNotReceived;
    private String loseMessage;

    public ResultAnnouncerPropertyConfigTest(String winMessageReceived, String winMessageNotReceived, String loseMessage) {
        this.winMessageReceived = winMessageReceived;
        this.winMessageNotReceived = winMessageNotReceived;
        this.loseMessage = loseMessage;
    }

    @Override
    public String getWinMessageReceived() {
        return winMessageReceived;
    }

    @Override
    public void setWinMessageReceived(String winMessageReceived) {
        this.winMessageReceived = winMessageReceived;
    }

    @Override
    public String getWinMessageNotReceived() {
        return winMessageNotReceived;
    }

    @Override
    public void getWinMessageNotReceived(String winMessageNotReceived) {
        this.winMessageNotReceived = winMessageNotReceived;
    }

    @Override
    public String getLoseMessage() {
        return this.loseMessage;
    }

    @Override
    public void setLoseMessage(String loseMessage) {
        this.loseMessage = loseMessage;
    }
}
