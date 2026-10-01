package domain.entity;

public class Transaction {
    private static int sequence = 1;

    private final int id;
    private final String description;
    private final long amount;
    private final TransactionType type;

    public Transaction(String description, long amount, TransactionType type) {
        this.id = sequence++;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
