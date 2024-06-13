package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record NumberReceiverRequestDto(List<Integer> inputNumbers) {
}
