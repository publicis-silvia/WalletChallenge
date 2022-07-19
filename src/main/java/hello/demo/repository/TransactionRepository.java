package hello.demo.repository;

import hello.demo.entity.Product;
import hello.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    boolean existsByProductId(Product p);

//    @Query(value = "select case when (count(transaction) > 0)  then true else false end  \n" +
//            "from Transaction transaction where transaction.walletId = :id")
//    boolean existsByWalletId(long id);


}