package hello;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity // This tells Hibernate to make a table out of this class
public class Crypto implements java.io.Serializable {
   
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    
    @Column(unique = true)
    private String coinname;
    
    @Column(unique = true)
    private String coincode;
    
    //@OneToMany(cascade = CascadeType.ALL, targetEntity = CryptoPairPrice.class)
    //@JoinColumn(name="coincode")
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "crypto")
    
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pairedcoincode")
    //private Set<CryptoPairPrice> cryptopairs; // = new HashSet<CryptoPairPrice>(0);
    
    
	//public Set<CryptoPairPrice> getCryptopairs() {
	//	return this.cryptopairs;
	//}

	//public void setCryptopairs(Set<CryptoPairPrice> cryptoPairs) {
	//	this.cryptopairs = cryptoPairs;
	//}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoinname() {
		return coinname;
	}

	public void setCoinname(String coinname) {
		this.coinname = coinname;
	}

	public String getCoincode() {
		return coincode;
	}

	public void setCoincode(String coincode) {
		this.coincode = coincode;
	}

	
    
    
}

