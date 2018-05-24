package br.com.diego.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FabricaDeConexoes {
	private static Connection connection;
	private static InitialContext initialContext;
	private static ConnectionFactory factory;
	
	public static Connection getConnection() throws NamingException, JMSException{
		if (connection != null){
			return connection;
		}
		initialContext = new InitialContext();
		factory = getParametroContexot("ConnectionFactory", ConnectionFactory.class);
		connection = factory.createConnection();
		
		return connection;
	}
	
	public static <T extends Object> T getParametroContexot(String valor, Class<T> tipoRetorno) throws NamingException{
		return tipoRetorno.cast(initialContext.lookup(valor));
	}
	
	public static void fecharConexao() throws JMSException, NamingException{
		connection.close();
		initialContext.close();
	}
	
}
