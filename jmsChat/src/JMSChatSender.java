
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSChatSender {

  private static String user = "Schober";
  private static String password = "";
  private static String url = "failover://tcp://127.0.0.1:61616";
  private static String subject = "VSDBChat";
	
  
  public static void main( String[] args ) {
		
	  // Create the connection.
	  Session session = null;
	  Connection connection = null;
	  MessageProducer producer = null;
	  Destination destination = null;
			
	  try {
	    	
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( user, password, url );
			connection = connectionFactory.createConnection();
			connection.start();
		
			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic( subject );
				  
			// Create the producer.
			producer = session.createProducer(destination);
			producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );
			
			// Create the message
			//TextMessage message = session.createTextMessage( "MaxMustermann [ xxx.xxx.xxx.xxx ]: This message was sent at (ms): " + System.currentTimeMillis() );
			TextMessage message = session.createTextMessage("sefesgsefs");
			producer.send(message);
			System.out.println( message.getText() );
			connection.stop();
	      
	  } catch (Exception e) {
	  	
	  	System.out.println("[MessageProducer] Caught: " + e);
	  	e.printStackTrace();
	  	
	  } finally {
	  	
			try { producer.close(); } catch ( Exception e ) {}
			try { session.close(); } catch ( Exception e ) {}
			try { connection.close(); } catch ( Exception e ) {}
			
	  }
      
  } // end main
	
}
