package pl.lotto.infrastructure.controller.numberreceiver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverRequestDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.Objects;
import java.util.Set;

import static pl.lotto.numberreceiver.NumberValidationResult.SUCCESS_MESSAGE;

@RequiredArgsConstructor
@RestController
public class NumberReceiverRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResultDto> inputNumbers(@RequestBody NumberReceiverRequestDto request) {
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(Set.copyOf(request.inputNumbers()));
        HttpStatus httpStatus = Objects.equals(result.status(), SUCCESS_MESSAGE)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, httpStatus);
    }
}
