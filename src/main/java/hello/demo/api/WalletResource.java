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
import hello.demo.service.WalletService;

public class WalletResource {
	private final WalletService service;

	WalletResource(WalletService service) {
		this.service = service;
	}

	@RequestMapping("api/wallets/v1/get")
	public List<Wallet> getAllWallets(Model model) {
		List<Wallet> wallets = service.getWallets();
		return wallets;
	}

	@RequestMapping("api/wallets/v1/get/{walletId}")
	public Wallet getWalletById(@PathVariable Long walletId) {
		Wallet wallet = service.getWalletById(walletId);

		if (wallet == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return wallet;
	}

	@RequestMapping("api/wallets/v1/get/{walletId}/statement")
	public Wallet getWalletStatement(@PathVariable Long walletId) {
		Wallet wallet = service.getWalletById(walletId);

		if (wallet == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return wallet;
	}

	@RequestMapping(value = "api/wallets/v1/create", method = RequestMethod.POST)
	public ResponseEntity<Object> addWallet(@RequestBody Wallet newWallet) {
		long walletId = service.addWallet(newWallet);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{walletId}").buildAndExpand(walletId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/wallets/v1/delete/{walletId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteWalletById(@PathVariable Long walletId) {
		service.deleteWalletById(walletId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/wallets/v1/update/{walletId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateWallet(@PathVariable long walletId, @RequestBody Wallet wallet) {
		service.updateWallet(walletId, wallet);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/wallets/v1/update/{operation}/{walletId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateWalletBalance(@PathVariable long walletId, @RequestBody Wallet wallet) {
		// TODO: Implement balance update, add or deduct
		service.updateWallet(walletId, wallet);

		return ResponseEntity.noContent().build();
	}
}