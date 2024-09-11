package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lotto.drawdategenerator.dto.DrawDate;

import java.time.Instant;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DrawDateGeneratorFacadeTest extends DrawDateGeneratorFacadeConfig {

    @ParameterizedTest
    @MethodSource("VALID_CURRENT_WEEK_DRAW_DATES")
    void should_return_current_week_friday_eight_pm_utc_time(ZonedDateTime ticketCreatedAt, ZonedDateTime expectedDrawDate) {
        //given
        DrawDateGeneratorFacade drawDateGeneratorFacade = new DrawDateGeneratorConfiguration()
                .drawDateGeneratorFacadeForTest(propertyConfigurable);
        //when
        DrawDate drawDateDto = drawDateGeneratorFacade.getNextDrawDate(ticketCreatedAt.toInstant());
        //then
        Instant drawDate = drawDateDto.drawDate();
        assertThat(drawDate)
                .isEqualTo(expectedDrawDate.toInstant());
    }

    @ParameterizedTest
    @MethodSource("VALID_NEXT_WEEK_DRAW_DATES")
    void should_return_next_week_friday_eight_pm_utc_time(ZonedDateTime ticketCreatedAt, ZonedDateTime expectedDrawDate) {
        //given
        DrawDateGeneratorFacade drawDateGeneratorFacade = new DrawDateGeneratorConfiguration()
                .drawDateGeneratorFacadeForTest(propertyConfigurable);
        //when
        DrawDate drawDateDto = drawDateGeneratorFacade.getNextDrawDate(ticketCreatedAt.toInstant());
        //then
        Instant drawDate = drawDateDto.drawDate();
        assertThat(drawDate)
                .isEqualTo(expectedDrawDate.toInstant());
    }
}