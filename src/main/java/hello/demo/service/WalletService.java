package hello.demo.service;

import java.util.List;
import java.util.Optional;

import hello.demo.entity.Wallet;
import hello.demo.repository.WalletRepository;

public class WalletService {

	private final WalletRepository repository;

	public WalletService(WalletRepository repository) {
		super();
		this.repository = repository;
	}

	public List<Wallet> getWallets() {
		List<Wallet> wallets = repository.findAll();
		return wallets;
	}

	public Wallet getWalletById(Long walletId) {
		Optional<Wallet> wallet = repository.findById(walletId);
		return wallet.get();
	}

	public long addWallet(Wallet newWallet) {
		Wallet wallet = repository.save(newWallet);
		return wallet.getId();
	}

	public void deleteWalletById(Long walletId) {
		repository.deleteById(walletId);
	}

	public void updateWallet(long walletId, Wallet wallet) {
		repository.deleteById(walletId);
		repository.save(wallet);
	}
}
