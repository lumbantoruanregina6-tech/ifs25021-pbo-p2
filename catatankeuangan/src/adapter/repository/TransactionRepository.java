package adapter.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return new ArrayList<>(transactions);
    }

    @Override
    public boolean removeById(int id) {
        return transactions.removeIf(transaction -> transaction.getId() == id);
    }

    @Override
    public List<Transaction> search(String keyword) {
        String searchKey = keyword.toLowerCase();
        return transactions.stream()
                .filter(transaction -> transaction.getDescription().toLowerCase().contains(searchKey))
                .toList();
    }

    @Override
    public List<Transaction> sort(SortOption option) {
        List<Transaction> copy = new ArrayList<>(transactions);
        Comparator<Transaction> comparator;

        switch (option) {
            case AMOUNT_DESC -> comparator = Comparator.comparingLong(Transaction::getAmount).reversed();
            case AMOUNT_ASC -> comparator = Comparator.comparingLong(Transaction::getAmount);
            case INCOME_FIRST -> comparator = (t1, t2) -> {
                if (t1.getType() == t2.getType()) {
                    return Long.compare(t2.getAmount(), t1.getAmount());
                }
                return t1.getType() == TransactionType.INCOME ? -1 : 1;
            };
            case EXPENSE_FIRST -> comparator = (t1, t2) -> {
                if (t1.getType() == t2.getType()) {
                    return Long.compare(t2.getAmount(), t1.getAmount());
                }
                return t1.getType() == TransactionType.EXPENSE ? -1 : 1;
            };
            default -> comparator = Comparator.comparingInt(Transaction::getId);
        }

        copy.sort(comparator);
        return copy;
    }

    @Override
    public long getBalance() {
        long balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }
}
