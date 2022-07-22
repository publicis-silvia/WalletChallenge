package hello.demo.service;

import java.util.List;
import java.util.Optional;

import hello.demo.entity.Wallet;
import hello.demo.repository.WalletRepository;

public class WalletService implements DataAccessService<Wallet> {

	private final WalletRepository repository;

	public WalletService(WalletRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Wallet> getAll() {
		List<Wallet> wallets = repository.findAll();
		return wallets;
	}

	@Override
	public Wallet getById(Long walletId) {
		Optional<Wallet> wallet = repository.findById(walletId);
		return wallet.get();
	}

	@Override
	public long add(Wallet newWallet) {
		Wallet wallet = repository.save(newWallet);
		return wallet.getId();
	}

	@Override
	public void deleteById(Long walletId) {
		repository.deleteById(walletId);
	}

	@Override
	public void update(long walletId, Wallet wallet) {
		repository.deleteById(walletId);
		repository.save(wallet);
	}
}
