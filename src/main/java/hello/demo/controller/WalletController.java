package hello.demo.controller;

import hello.demo.entity.Person;
import hello.demo.entity.Product;
import hello.demo.entity.Wallet;
import hello.demo.repository.PersonRepository;
import hello.demo.repository.WalletRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WalletController {

    private final WalletRepository repository;
    private final PersonRepository personRepository;


    WalletController(WalletRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }



    @GetMapping("/redirectAddWalletPage")
    public ModelAndView list2(@RequestParam Long personId, Model model) {
        ModelAndView mav = new ModelAndView("addWallet");
        Wallet wallet = new Wallet();
        wallet.setPersonId(personId);
        mav.addObject("wallet", wallet);
        return mav;
    }


    @PostMapping("/postWallet")
    public String addProduct(@ModelAttribute Wallet newWallet, Model model) {
        repository.save(newWallet);
        personRepository.findById(newWallet.getPersonId())
                .map(person -> {
                    person.setWallet(newWallet);
                    return personRepository.save(person);
                });
        model.addAttribute("people", personRepository.findAll());

        return "people";
    }


    @GetMapping("/redirectAUpdateWalletPage")
    public ModelAndView updateWallet(@RequestParam Long walletId, Model model) {
        ModelAndView mav = new ModelAndView("updateWallet");
        Wallet wallet = repository.findById(walletId).get();
        mav.addObject("wallet", wallet);
        return mav;
    }


    @GetMapping("/putWallet")
    public String replaceProduct(@ModelAttribute Wallet updatedWallet, Model model) {

        repository.findById(updatedWallet.getId())
                .map(wallet -> {
                    wallet.setBalance(updatedWallet.getBalance());
                    return repository.save(wallet);
                });
        model.addAttribute("people", personRepository.findAll());

        return "people";
    }

}
