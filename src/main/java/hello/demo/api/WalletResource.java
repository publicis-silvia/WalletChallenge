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

import hello.demo.entity.Wallet;
import hello.demo.service.DataAccessService;

public class WalletResource {
	private final DataAccessService<Wallet> service;

	WalletResource(DataAccessService<Wallet> service) {
		this.service = service;
	}

	@RequestMapping("api/v1/wallets")
	public List<Wallet> getAllWallets(Model model) {
		List<Wallet> wallets = service.getAll();
		return wallets;
	}

	@RequestMapping("api/v1/wallets/{walletId}")
	public Wallet getWalletById(@PathVariable Long walletId) {
		Wallet wallet = service.getById(walletId);

		if (wallet == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return wallet;
	}

	@RequestMapping("api/v1/wallets/{walletId}/statement")
	public Wallet getWalletStatement(@PathVariable Long walletId) {
		Wallet wallet = service.getById(walletId);

		if (wallet == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return wallet;
	}

	@RequestMapping(value = "api/v1/wallets", method = RequestMethod.POST)
	public ResponseEntity<Object> addWallet(@RequestBody Wallet newWallet) {
		long walletId = service.add(newWallet);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{walletId}").buildAndExpand(walletId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/v1/wallets/{walletId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteWalletById(@PathVariable Long walletId) {
		service.deleteById(walletId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/v1/wallets/{walletId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateWallet(@PathVariable long walletId, @RequestBody Wallet wallet) {
		service.update(walletId, wallet);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/v1/wallets/{operation}/{walletId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateWalletBalance(@PathVariable long walletId, @RequestBody Wallet wallet) {
		// TODO: Implement balance update, add or deduct
		service.update(walletId, wallet);

		return ResponseEntity.noContent().build();
	}
}