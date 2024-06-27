package pl.lotto.drawdategenerator;

public interface DrawDatePropertyConfigurable {

    int getDrawDayOfWeek();

    void setDrawDayOfWeek(int dayOfWeek);

    int getDrawHour();

    void setDrawHour(int drawHour);

    int getDrawMinute();

    void setDrawMinute(int drawMinute);

    int getDrawSecond();

    void setDrawSecond(int drawSecond);

}
