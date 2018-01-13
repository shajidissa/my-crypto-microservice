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

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;


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
	
	@PostMapping(path = "/createById")
	public ResponseEntity<Void> createById(@RequestBody String id, @RequestBody String coinname) {
		
		Crypto n = new Crypto();
		n.setCoinname(coinname);
		n.setCoincode(id);
		
		Crypto p = cryptoRepository.save(n);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<Void> create(@Valid @RequestBody Crypto crypto) {

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
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Crypto> getCryptos() {
		// This returns a JSON or XML with the users
		return cryptoRepository.findAll();
	}
	
	@GetMapping(path="/get")
	public @ResponseBody Crypto getCryptoByID(@RequestParam int id) {
		// This returns a JSON or XML with the users
		return cryptoRepository.findCryptoById(id);
	}
	
	@DeleteMapping(path = "/delete")
	 public ResponseEntity <String> delete(int id) {
		cryptoRepository.delete(getCryptoByID(id));
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(path="/myfav")
	public @ResponseBody Iterable<MyFavoriteCrypto> getMyFavouriteCryptos() {
		// This returns a JSON or XML with the users
		
		List<Crypto> myfav = (List<Crypto>) cryptoRepository.findAll();
		
		List<MyFavoriteCrypto> myfavWithPrices = new ArrayList<MyFavoriteCrypto>();
		
		for(Crypto c: myfav)
		{
			MyFavoriteCrypto afav = new MyFavoriteCrypto();
			afav.setId(c.getId());
			afav.setCoincode(c.getCoincode());
			afav.setCoinname(c.getCoinname());
			
			BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(/*"API-KEY", "SECRET"*/);
			BinanceApiRestClient client = factory.newRestClient();
			
			client.ping();
			
			Long serverTime = client.getServerTime();
			System.out.println(serverTime);
			
			System.out.println(c.getCoincode()+"ETH");
			
			try {
				TickerStatistics tickerStatistics1 = client.get24HrPriceStatistics(c.getCoincode()+"ETH");
				System.out.println(tickerStatistics1.getLastPrice());
				afav.setETH(tickerStatistics1.getLastPrice());
				TickerStatistics tickerStatistics2 = client.get24HrPriceStatistics(c.getCoincode()+"BTC");
				System.out.println(tickerStatistics2.getLastPrice());
				afav.setBTC(tickerStatistics2.getLastPrice());
				
			}
			catch (Exception e) {}
			
			myfavWithPrices.add(afav);
			
			
			
			
			
		}
		
		return myfavWithPrices;
		//return cryptoRepository.findAll();
	}
	
	//https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC,USD,EUR,GBP,ETH
	
	// https://github.com/binance-exchange/binance-java-api
		
	@GetMapping(path="/getprice")
	public String getPrice(@RequestParam String pair) {
		// This returns a JSON or XML with the users
		
		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(/*"API-KEY", "SECRET"*/);
		BinanceApiRestClient client = factory.newRestClient();
		
		client.ping();
		
		Long serverTime = client.getServerTime();
		System.out.println(serverTime);
		
		OrderBook orderBook = client.getOrderBook(pair, 10);
		List<OrderBookEntry> asks = orderBook.getAsks();
		OrderBookEntry firstAskEntry = asks.get(0);
		System.out.println(pair + " " + firstAskEntry.getPrice() + " / " + firstAskEntry.getQty());
		
		List<Candlestick> candlesticks = client.getCandlestickBars(pair, CandlestickInterval.WEEKLY);
		System.out.println(candlesticks);
		
		TickerStatistics tickerStatistics = client.get24HrPriceStatistics(pair);
		System.out.println(tickerStatistics.getLastPrice());
		
		List<TickerPrice> allPrices = client.getAllPrices();
		System.out.println(allPrices);
		
		return pair + " " + firstAskEntry.getPrice() + " / " + firstAskEntry.getQty();
	}

}
