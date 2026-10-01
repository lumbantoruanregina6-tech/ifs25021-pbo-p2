package usecase;

import java.util.List;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

public class FinanceUseCase {
    private final ITransactionRepository repository;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction addTransaction(String description, long amount, TransactionType type) {
        if (description == null || description.isBlank() || amount <= 0) {
            return null;
        }
        Transaction transaction = new Transaction(description, amount, type);
        repository.add(transaction);
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return repository.getAll();
    }

    public long getBalance() {
        return repository.getBalance();
    }

    public List<Transaction> searchTransactions(String keyword) {
        return repository.search(keyword);
    }

    public List<Transaction> sortTransactions(SortOption option) {
        return repository.sort(option);
    }

    public boolean removeTransaction(int id) {
        return repository.removeById(id);
    }
}
