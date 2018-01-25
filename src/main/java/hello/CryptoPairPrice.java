package hello;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity // This tells Hibernate to make a table out of this class
public class CryptoPairPrice implements java.io.Serializable {
	
    //@Id
   // @GeneratedValue(strategy=GenerationType.AUTO)  
    //private Integer pairedid;
    
    //@Id
    private Integer pairedcoinid;
    
    
    private String pairedcoincode;
    
    private String pairedcoincodematch;
    
    @Id
    private String pairedcoin;
    
    private String pairedprice;
    
    private Long currenttime;

    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},targetEntity = Crypto.class)
    //@ManyToOne
    //@JoinColumn(name = "coincode")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coincode")	
    private Crypto crypto;

	/*public Integer getPairedid() {
		return pairedid;
	}

	public void setPairedid(Integer pairedid) {
		this.pairedid = pairedid;
	}*/

    
	public Integer getPairedcoinid() {
		return pairedcoinid;
	}

	public String getPairedcoincodematch() {
		return pairedcoincodematch;
	}

	public void setPairedcoincodematch(String pairedcoincodematch) {
		this.pairedcoincodematch = pairedcoincodematch;
	}

	public void setPairedcoinid(Integer pairedcoinid) {
		this.pairedcoinid = pairedcoinid;
	}

	public String getPairedcoincode() {
		return pairedcoincode;
	}

	public void setPairedcoincode(String pairedcoincode) {
		this.pairedcoincode = pairedcoincode;
	}

	public String getPairedcoin() {
		return pairedcoin;
	}

	public void setPairedcoin(String pairedcoin) {
		this.pairedcoin = pairedcoin;
	}

	public String getPairedprice() {
		return pairedprice;
	}

	public void setPairedprice(String pairedprice) {
		this.pairedprice = pairedprice;
	}

	public Crypto getCrypto() {
		return crypto;
	}

	public void setCrypto(Crypto crypto) {
		this.crypto = crypto;
	}

	public Long getCurrenttime() {
		return currenttime;
	}

	public void setCurrenttime(Long currenttime) {
		this.currenttime = currenttime;
	}
    
    
    
    
	
	
    
    
}

