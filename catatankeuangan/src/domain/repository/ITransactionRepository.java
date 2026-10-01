package domain.repository;

import java.util.List;

import domain.entity.SortOption;
import domain.entity.Transaction;

public interface ITransactionRepository {
    void add(Transaction transaction);
    List<Transaction> getAll();
    boolean removeById(int id);
    List<Transaction> search(String keyword);
    List<Transaction> sort(SortOption option);
    long getBalance();
}
