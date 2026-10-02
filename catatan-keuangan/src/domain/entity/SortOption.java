package domain.entity;

import java.util.Comparator;

public enum SortOption {
    AMOUNT_ASC(Comparator.comparingLong(Transaction::getAmount)),
    AMOUNT_DESC(Comparator.comparingLong(Transaction::getAmount).reversed()),
    INCOME_FIRST(typeFirst(TransactionType.INCOME)),
    EXPENSE_FIRST(typeFirst(TransactionType.EXPENSE));

    private final Comparator<Transaction> comparator;

    SortOption(Comparator<Transaction> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Transaction> comparator() {
        return comparator;
    }

    private static Comparator<Transaction> typeFirst(TransactionType first) {
        return (t1, t2) -> {
            if (t1.getType() == t2.getType()) {
                return Long.compare(t2.getAmount(), t1.getAmount());
            }
            return t1.getType() == first ? -1 : 1;
        };
    }
}
