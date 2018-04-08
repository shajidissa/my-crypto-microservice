package hello;

//Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Example {
// Find your Account Sid and Token at twilio.com/user/account
public static final String ACCOUNT_SID = "ACfaa0109e3e76c788e083eac7e8f4486c";
public static final String AUTH_TOKEN = "869985f3d5b9e23899b9b20a43e57e85";

public static void main(String[] args) {
 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

 Message message = Message.creator(new PhoneNumber("+447703332511"),
     new PhoneNumber("+441992351786"), "Shajid Issa").create();

 System.out.println(message.getSid());
}
}