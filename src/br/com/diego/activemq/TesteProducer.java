package br.com.diego.activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.NamingException;

public class TesteProducer {
	public static void main(String[] args) throws NamingException, JMSException {
		Connection connection = FabricaDeConexoes.getConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = FabricaDeConexoes.getParametroContexot("MyQueue", Destination.class);
		
		MessageProducer producer = session.createProducer(destination);
		
		for(int i = 0; i < 1000; i ++) { 
		    Message message = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
		    producer.send(message);
		}
		
		
		session.close();
		FabricaDeConexoes.fecharConexao();
	}
}
