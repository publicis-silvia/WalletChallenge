package hello.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hello.demo.entity.Transaction;
import hello.demo.repository.TransactionRepository;

@Service
public class TransactionService implements DataAccessService<Transaction> {

	private final TransactionRepository repository;

	public TransactionService(TransactionRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Transaction> getAll() {
		List<Transaction> transactions = repository.findAll();
		return transactions;
	}

	@Override
	public Transaction getById(Long transactionId) {
		Optional<Transaction> transaction = repository.findById(transactionId);
		return transaction.get();
	}

	@Override
	public long add(Transaction newTransaction) {
		Transaction transaction = repository.save(newTransaction);
		return transaction.getId();
	}

	@Override
	public void deleteById(Long transactionId) {
		repository.deleteById(transactionId);
	}

	@Override
	public void update(long transactionId, Transaction transaction) {
		repository.deleteById(transactionId);
		repository.save(transaction);
	}

}
