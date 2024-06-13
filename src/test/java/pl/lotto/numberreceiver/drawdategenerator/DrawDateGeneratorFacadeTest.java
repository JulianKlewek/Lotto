package pl.lotto.numberreceiver.drawdategenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lotto.drawdategenerator.DrawDateGeneratorConfig;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class DrawDateGeneratorFacadeTest implements DrawDateTestsConstants {


    @ParameterizedTest
    @MethodSource("VALID_NEXT_DRAW_DATES")
    public void should_return_friday_eight_pm(LocalDateTime ticketCreatedAt, LocalDateTime expectedDrawDate) {
        //given
        Clock clock = Clock.fixed(ticketCreatedAt.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        DrawDateGeneratorFacade drawDateGeneratorFacade = new DrawDateGeneratorConfig().drawDateGeneratorFacadeForTest(clock);
        //when
        DrawDateDto drawDateDto = drawDateGeneratorFacade.getNextDrawDate(ticketCreatedAt);
        //then
        assertThat(drawDateDto.drawDate()).isEqualTo(expectedDrawDate);
    }
}