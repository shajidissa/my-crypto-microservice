package hello;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller    
@RequestMapping(path="/crypto") 
public class CryptoController  {
	
	@Autowired
	private CryptoRepository cryptoRepository;

	
	@GetMapping(path="/list") // Map ONLY GET Requests
	public @ResponseBody String listCrypto () {
		
		final String uri = "https://min-api.cryptocompare.com/data/all/coinlist";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    
		return result;	
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<Void> createpatient(@Valid @RequestBody Crypto crypto) {

		Crypto n = new Crypto();
		n.setCoinname(crypto.getCoinname());
		n.setCoincode(crypto.getCoincode());
		
		Crypto p = cryptoRepository.save(n);
		
		//if (course == null)
		//	return ResponseEntity.noContent().build();

		//URI location = ServletUriComponentsBuilder.fromPath("/getpatient/{id}").buildAndExpand(p.getId()).toUri();

		//return ResponseEntity.created().build();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
