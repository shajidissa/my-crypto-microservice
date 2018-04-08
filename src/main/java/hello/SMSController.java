package hello;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Controller    
@RequestMapping(path="/sms") 
public class SMSController  {
	
	public static final String ACCOUNT_SID = "ACfaa0109e3e76c788e083eac7e8f4486c";
	public static final String AUTH_TOKEN = "869985f3d5b9e23899b9b20a43e57e85";

	
	@GetMapping(path="/list") // Map ONLY GET Requests
	public @ResponseBody String listCrypto () {
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		 Message message = Message.creator(new PhoneNumber("+447703332511"),
		     new PhoneNumber("+441992351786"), "Shajid Issa").create();

		 System.out.println(message.getSid());
		
	    
		return "";	
	}
	
	
}
