package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CryptoPairPriceRepository extends CrudRepository<CryptoPairPrice, Long> {

	public CryptoPairPrice findCryptoPairPriceByPairedcoin(String pairedcoin);
	
	public CryptoPairPrice findCryptoPairPriceByPairedcoinid(int pairedcoinid);
	
	@Transactional
	@Modifying
	@Query("delete from CryptoPairPrice p where p.pairedcoinid = :id")
	public void deleteById(@Param("id") int id);
	
}
