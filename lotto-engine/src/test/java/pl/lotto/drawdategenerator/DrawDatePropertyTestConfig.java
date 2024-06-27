package pl.lotto.drawdategenerator;

public class DrawDatePropertyTestConfig implements DrawDatePropertyConfigurable {

    private int drawDayOfWeek;
    private int drawHour;
    private int drawMinute;
    private int drawSecond;

    public DrawDatePropertyTestConfig(int drawDayOfWeek, int drawHour, int drawMinute, int drawSecond) {
        this.drawDayOfWeek = drawDayOfWeek;
        this.drawHour = drawHour;
        this.drawMinute = drawMinute;
        this.drawSecond = drawSecond;
    }

    @Override
    public int getDrawDayOfWeek() {
        return drawDayOfWeek;
    }

    @Override
    public void setDrawDayOfWeek(int drawDayOfWeek) {
        this.drawDayOfWeek = drawDayOfWeek;
    }

    @Override
    public int getDrawHour() {
        return drawHour;
    }

    @Override
    public void setDrawHour(int drawHour) {
        this.drawHour = drawHour;
    }

    @Override
    public int getDrawMinute() {
        return drawMinute;
    }

    @Override
    public void setDrawMinute(int drawMinute) {
        this.drawMinute = drawMinute;
    }

    @Override
    public int getDrawSecond() {
        return drawSecond;
    }

    @Override
    public void setDrawSecond(int drawSecond) {
        this.drawSecond = drawSecond;
    }
}
