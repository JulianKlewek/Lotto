package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.*;

import static java.time.Clock.*;
import static org.assertj.core.api.Assertions.assertThat;

class DrawDateGeneratorFacadeTest implements DrawDateTestsConstants {

    @ParameterizedTest
    @MethodSource("VALID_NEXT_DRAW_DATES")
    void should_return_friday_eight_pm(ZonedDateTime ticketCreatedAt, ZonedDateTime expectedDrawDate) {
        //given
        Clock clock = fixed(ticketCreatedAt.toInstant(),ticketCreatedAt.getZone());
        DrawDateGeneratorFacade drawDateGeneratorFacade = new DrawDateGeneratorConfig().drawDateGeneratorFacadeForTest(clock);
        //when
        DrawDateDto drawDateDto = drawDateGeneratorFacade.getNextDrawDate(ticketCreatedAt);
        //then
        assertThat(drawDateDto.drawDate()).isEqualTo(expectedDrawDate);
    }
}