package hello.demo.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hello.demo.entity.Transaction;
import hello.demo.service.DataAccessService;

public class TransactionResource {

	private final DataAccessService<Transaction> service;

	TransactionResource(DataAccessService<Transaction> service) {
		this.service = service;
	}

	@RequestMapping("api/v1/transactions")
	public List<Transaction> getAllTransactions(Model model) {
		List<Transaction> transactions = service.getAll();
		return transactions;
	}

	@RequestMapping("api/v1/transactions/{transactionId}")
	public Transaction getTransactionById(@PathVariable Long transactionId) {
		Transaction transaction = service.getById(transactionId);

		if (transaction == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return transaction;
	}

	@RequestMapping(value = "api/v1/transactions", method = RequestMethod.POST)
	public ResponseEntity<Object> addTransaction(@RequestBody Transaction newTransaction) {
		long transactionId = service.add(newTransaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{transactionId}")
				.buildAndExpand(transactionId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/v1/transactions/{transactionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTransactionById(@PathVariable Long transactionId) {
		service.deleteById(transactionId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/v1/transactions/{transactionId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTransaction(@PathVariable long transactionId,
			@RequestBody Transaction transaction) {
		service.update(transactionId, transaction);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/v1/transactions/sale", method = RequestMethod.POST)
	public ResponseEntity<Object> sale(@RequestBody Transaction newTransaction) {
		// TODO: implement sale
		long transactionId = service.add(newTransaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{transactionId}")
				.buildAndExpand(transactionId).toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/v1/transactions/purchase", method = RequestMethod.POST)
	public ResponseEntity<Object> purchase(@RequestBody Transaction newTransaction) {
		// TODO: implement purchase
		long transactionId = service.add(newTransaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{transactionId}")
				.buildAndExpand(transactionId).toUri();

		return ResponseEntity.created(location).build();
	}

}
