package br.com.diego.activemq;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

public class TesteConsumidor {
	public static void main(String[] args) throws NamingException, JMSException {
		Connection connection = FabricaDeConexoes.getConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = FabricaDeConexoes.getParametroContexot("MyQueue", Destination.class);
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message mensagem) {
				TextMessage textMessage  = (TextMessage)mensagem;
		        try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//Message mensagem = consumer.receive();
		
		new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        FabricaDeConexoes.fecharConexao();
		
	}
}
