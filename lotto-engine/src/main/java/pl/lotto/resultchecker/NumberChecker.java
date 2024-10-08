package pl.lotto.resultchecker;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.numberreceiver.dto.TicketPayload;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class NumberChecker {

    private static final int MINIMAL_AMOUNT_OF_MATCHING_NUMBERS = 4;
    private static final boolean DEFAULT_COLLECTED_REWARD_VALUE = false;

    List<WinningTicket> checkTicketsNumbers(List<TicketPayload> userTickets, WinningNumbersResponse winningNumbers) {
        List<WinningTicket> winningTickets = new LinkedList<>();
        for (TicketPayload ticket : userTickets) {
            if (isTicketContainsWinningNumbers(ticket.numbers(), winningNumbers.numbers())) {
                int amountOfSameNumbers = checkAmountOfMatchedNumbers(ticket.numbers(), winningNumbers.numbers());
                if (amountOfSameNumbers >= MINIMAL_AMOUNT_OF_MATCHING_NUMBERS) {
                    WinningTicket winningTicket = WinningTicket.builder()
                            .amountOfCorrectNumbers(amountOfSameNumbers)
                            .lotteryNumber(winningNumbers.lotteryNumber())
                            .userNumbers(ticket.numbers())
                            .hash(ticket.hash())
                            .drawDate(ticket.drawDate())
                            .collectedReward(DEFAULT_COLLECTED_REWARD_VALUE)
                            .build();
                    winningTickets.add(winningTicket);
                }
            }
        }
        return winningTickets;
    }

    private boolean isTicketContainsWinningNumbers(List<Integer> ticket, List<Integer> winningNumbers) {
        return !Collections.disjoint(ticket, winningNumbers);
    }

    private int checkAmountOfMatchedNumbers(List<Integer> ticket, List<Integer> winningNumbers) {
        int amountOfSameNumbers = 0;
        for (int number : ticket) {
            for (int winningNumber : winningNumbers) {
                if (number == winningNumber) {
                    amountOfSameNumbers++;
                }
            }
        }
        return amountOfSameNumbers;
    }

    public int checkTicketNumbers(List<Integer> userNumbers, List<Integer> winningNumbers) {
        return findAmountMatchingNumbers(userNumbers, winningNumbers);
    }

    private int findAmountMatchingNumbers(List<Integer> userTypedNumbers, List<Integer> winningNumbers) {
        return userTypedNumbers.stream()
                .filter(winningNumbers::contains)
                .toList()
                .size();
    }

    public boolean isEnoughMatchingNumbersAmountToWin(int amountOfCorrectNumbers) {
        return amountOfCorrectNumbers >= MINIMAL_AMOUNT_OF_MATCHING_NUMBERS;
    }
}
