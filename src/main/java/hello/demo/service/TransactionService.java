package hello.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hello.demo.entity.Transaction;
import hello.demo.repository.TransactionRepository;

@Service
public class TransactionService {

	private final TransactionRepository repository;

	public TransactionService(TransactionRepository repository) {
		super();
		this.repository = repository;
	}

	public List<Transaction> getTransactions() {
		List<Transaction> transactions = repository.findAll();
		return transactions;
	}

	public Transaction getTransactionById(Long transactionId) {
		Optional<Transaction> transaction = repository.findById(transactionId);
		return transaction.get();
	}

	public long addTransaction(Transaction newTransaction) {
		Transaction transaction = repository.save(newTransaction);
		return transaction.getId();
	}

	public void deleteTransactionById(Long transactionId) {
		repository.deleteById(transactionId);
	}

	public void updateTransaction(long transactionId, Transaction transaction) {
		repository.deleteById(transactionId);
		repository.save(transaction);
	}

}
