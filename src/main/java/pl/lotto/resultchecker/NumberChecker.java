package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class NumberChecker {

    private static final int MINIMAL_AMOUNT_OF_WINNING_NUMBERS = 4;
    private static final boolean DEFAULT_COLLECTED_REWARD_VALUE = false;

    List<WinningTicket> checkTicketsNumbers(List<TicketDto> userTickets, WinningNumbersDto winningNumbers) {
        List<WinningTicket> winningTickets = new LinkedList<>();
        for (TicketDto ticket : userTickets) {
            if (isTicketContainWinningNumbers(ticket.numbers(), winningNumbers.numbers())) {
                int amountOfSameNumbers = checkAmountOfWinningNumbers(ticket.numbers(), winningNumbers.numbers());
                if (amountOfSameNumbers >= MINIMAL_AMOUNT_OF_WINNING_NUMBERS) {
                    WinningTicket winningTicket = WinningTicket.builder()
                            .amountOfCorrectNumbers(amountOfSameNumbers)
                            .lotteryNumber(winningNumbers.lotteryNumber())
                            .numbers(ticket.numbers())
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

    private boolean isTicketContainWinningNumbers(List<Integer> ticket, List<Integer> winningNumbers) {
        return !Collections.disjoint(ticket, winningNumbers);
    }

    private int checkAmountOfWinningNumbers(List<Integer> ticket, List<Integer> winningNumbers) {
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
}
