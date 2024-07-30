package pl.lotto.infrastructure.propertyconfig.drawdategenerator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import pl.lotto.drawdategenerator.DrawDatePropertyConfigurable;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "lotto.time")
public class DrawDateGeneratorPropertyConfig implements DrawDatePropertyConfigurable {

    private int drawDayOfWeek;
    private int drawHour;
    private int drawMinute;
    private int drawSecond;

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
