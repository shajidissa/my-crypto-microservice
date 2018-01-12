package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CryptoRepository extends CrudRepository<Crypto, Long> {

	public Crypto findCryptoById(int id);
	
	public List<Crypto> findById(int id);
	
	@Query("from Crypto p where p.coinname like %:coinname%")
	public List<Crypto> findByCoinname(@Param("coinname") String coinname);
	
	@Query("from Crypto p where p.coincode like %:coincode%")
	public List<Crypto> findByCoincode(@Param("coincode") String coincode);
	
	@Transactional
	@Modifying
	@Query("delete from Crypto p where p.id = :id")
	public void deleteById(@Param("id") int id);
}
