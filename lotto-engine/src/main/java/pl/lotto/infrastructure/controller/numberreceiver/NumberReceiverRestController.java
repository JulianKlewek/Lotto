package pl.lotto.infrastructure.controller.numberreceiver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverRequest;
import pl.lotto.numberreceiver.dto.NumberReceiverResult;

import java.util.List;
import java.util.Objects;

import static pl.lotto.numberreceiver.NumberValidationResult.SUCCESS_MESSAGE;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
@RequestMapping("lottery")
public class NumberReceiverRestController implements NumberReceiverApi {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/input-numbers")
    public ResponseEntity<NumberReceiverResult> inputNumbers(@RequestBody NumberReceiverRequest request) {
        List<Integer> numbersList = request.inputNumbers();
        NumberReceiverResult result = numberReceiverFacade.inputNumbers(numbersList);
        HttpStatus httpStatus = Objects.equals(result.status(), SUCCESS_MESSAGE)
                ? HttpStatus.CREATED
                : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, httpStatus);
    }
}
