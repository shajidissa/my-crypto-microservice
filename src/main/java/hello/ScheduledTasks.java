package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
	private CryptoRepository cryptoRepository;
    
    @Autowired
	private CryptoPairPriceRepository cryptoPairPriceRepository;
    
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(/*"API-KEY", "SECRET"*/);
	BinanceApiRestClient client = factory.newRestClient();
    
    @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() {
    	
    	
    	List<Crypto> myfav = (List<Crypto>) cryptoRepository.findAll();
    	
    	for(Crypto c: myfav)
		{
		
    		client.ping();
    		
    		Long serverTime = client.getServerTime();
    		System.out.println(serverTime);
    		
    		System.out.println(c.getCoincode()+ "ETH");
    		String s1 = "0";
    		try {
    			TickerStatistics tickerStatistics1 = client.get24HrPriceStatistics(c.getCoincode()+ "ETH");
    			System.out.println(tickerStatistics1.getLastPrice());
    			s1 = tickerStatistics1.getLastPrice();
    		}
    		catch (Exception e) {}
    		
    		
    		CryptoPairPrice cpp = new CryptoPairPrice();
    		cpp.setPairedcoin(c.getCoincode()+ "ETH");
    		cpp.setPairedcoincode(c.getCoincode());
    		cpp.setPairedcoinid(c.getId());
    		cpp.setPairedcoincodematch("ETH");
    		//cpp.setPairedid(pairedid);
    		cpp.setPairedprice(s1);
    		cpp.setCurrenttime(serverTime);
    		cpp.setCrypto(c);
    		cryptoPairPriceRepository.save(cpp);
    		
    		
    		System.out.println(c.getCoincode()+ "BTC");
    		String s2 = "0";
    		try {
    			TickerStatistics tickerStatistics1 = client.get24HrPriceStatistics(c.getCoincode()+ "BTC");
    			System.out.println(tickerStatistics1.getLastPrice());
    			s2 = tickerStatistics1.getLastPrice();
    		}
    		catch (Exception e) {}
    		
    		
    		
    		CryptoPairPrice cpp2 = new CryptoPairPrice();
    		cpp2.setPairedcoin(c.getCoincode() + "BTC");
    		cpp2.setPairedcoincode(c.getCoincode());
    		cpp2.setPairedcoinid(c.getId());
    		cpp2.setPairedcoincodematch("BTC");
    		//cpp.setPairedid(pairedid);
    		cpp2.setPairedprice(s2);
    		cpp2.setCurrenttime(serverTime);
    		cpp2.setCrypto(c);
    		cryptoPairPriceRepository.save(cpp2);
    		
		}
		
    	/*
		client.ping();
		
		Long serverTime = client.getServerTime();
		System.out.println(serverTime);
		
		System.out.println("LTCETH");
		
    	try {
			TickerStatistics tickerStatistics1 = client.get24HrPriceStatistics("LTCETH");
			System.out.println(tickerStatistics1.getLastPrice());
			TickerStatistics tickerStatistics2 = client.get24HrPriceStatistics("LTCBTC");
			System.out.println(tickerStatistics2.getLastPrice());
			
			
		}
		catch (Exception e) {}
		*/
    	
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}