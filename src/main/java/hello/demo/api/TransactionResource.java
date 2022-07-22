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
import hello.demo.service.TransactionService;

public class TransactionResource {

	private final TransactionService service;

	TransactionResource(TransactionService service) {
		this.service = service;
	}

	@RequestMapping("api/transactions/v1/get")
	public List<Transaction> getAllTransactions(Model model) {
		List<Transaction> transactions = service.getTransactions();
		return transactions;
	}

	@RequestMapping("api/transactions/v1/get/{transactionId}")
	public Transaction getTransactionById(@PathVariable Long transactionId) {
		Transaction transaction = service.getTransactionById(transactionId);

		if (transaction == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return transaction;
	}

	@RequestMapping(value = "api/transactions/v1/create", method = RequestMethod.POST)
	public ResponseEntity<Object> addTransaction(@RequestBody Transaction newTransaction) {
		long transactionId = service.addTransaction(newTransaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{transactionId}")
				.buildAndExpand(transactionId).toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/transactions/v1/delete/{transactionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTransactionById(@PathVariable Long transactionId) {
		service.deleteTransactionById(transactionId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/transactions/v1/update/{transactionId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTransaction(@PathVariable long transactionId,
			@RequestBody Transaction transaction) {
		service.updateTransaction(transactionId, transaction);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/transactions/v1/sale", method = RequestMethod.POST)
	public ResponseEntity<Object> sale(@RequestBody Transaction newTransaction) {
		// TODO: implement sale
		long transactionId = service.addTransaction(newTransaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{transactionId}")
				.buildAndExpand(transactionId).toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/transactions/v1/purchase", method = RequestMethod.POST)
	public ResponseEntity<Object> purchase(@RequestBody Transaction newTransaction) {
		// TODO: implement purchase
		long transactionId = service.addTransaction(newTransaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{transactionId}")
				.buildAndExpand(transactionId).toUri();

		return ResponseEntity.created(location).build();
	}

}
