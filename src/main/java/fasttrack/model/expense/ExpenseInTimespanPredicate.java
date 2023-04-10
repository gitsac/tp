package fasttrack.model.expense;

import java.time.LocalDate;
import java.util.function.Predicate;

import fasttrack.logic.parser.ParserUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ExpenseInTimespanPredicate implements Predicate<Expense> {
    private final ParserUtil.Timespan timespan;
    private final LocalDate earliestDate;

    /**
     * Creates an {@code ExpenseInTimespanPredicate} which returns true if the given date is after the earliest date
     * in the timespan.
     * @param timespan Timespan of week, month or year
     */
    public ExpenseInTimespanPredicate(ParserUtil.Timespan timespan) {
        this.timespan = timespan;
        this.earliestDate = ParserUtil.getDateByTimespan(timespan);
    }

    /**
     * Creates an {@code ExpenseInTimespanPredicate} which returns true if the given date is after {@code earliestDate}.
     * @param earliestDate LocalDate of the earliestDate this predicate will return True for
     */
    public ExpenseInTimespanPredicate(LocalDate earliestDate) {
        this.timespan = null;
        this.earliestDate = earliestDate;
    }

    @Override
    public boolean test(Expense expense) {
        return expense.getDate().isAfter(earliestDate) || expense.getDate().isEqual(earliestDate);
    }

    /**
     * Get Timespan that this {@code ExpenseInCategoryPredicate} is generated by.
     * @return {@code Timespan} enum indicating week, month or year.
     */
    public ParserUtil.Timespan getTimespan() {
        return this.timespan;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseInTimespanPredicate // instanceof handles nulls
                && earliestDate.equals(((ExpenseInTimespanPredicate) other).earliestDate)); // state check
    }

}