package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller    
@RequestMapping(path="/crypto") 
public class CryptoController  {
	
	
	@GetMapping(path="/list") // Map ONLY GET Requests
	public @ResponseBody String listCrypto () {
		
		final String uri = "https://min-api.cryptocompare.com/data/all/coinlist";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    
		return result;
		
		
	}
	
	

}
