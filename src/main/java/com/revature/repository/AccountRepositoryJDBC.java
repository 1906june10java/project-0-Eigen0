package com.revature.repository;
/**
 * Class: Data Acess Object
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.util.ConnectionUtil;


public class AccountRepositoryJDBC implements AccountRepository {

	private static final Logger LOGGER= Logger.getLogger(AccountRepositoryJDBC.class);
	
	@Override
	public Account findByAccountNumber(long accountNumber) {
		LOGGER.trace("Entering find account by account number with parametrs: "+accountNumber);
		try(Connection connection=ConnectionUtil.getConnection()){
			int parameterIndex=0;
			String sql="SELECT * FROM ACCOUNT WHERE A_ACCOUNT_NUMBER= ?";
			
			 PreparedStatement statement =connection.prepareStatement(sql);
			statement.setLong(++parameterIndex, accountNumber);

			ResultSet result=statement.executeQuery();
			
			if(result.next()) {
				return new Account(
						result.getLong("A_ID"),
						result.getLong("A_BALANCE"),
						result.getLong("A_ACCOUNT_NUMBER"),
						result.getLong("A_PIN")

						);
			}
		}catch(SQLException e) {
			LOGGER.error("Could not find account.", e);
		}
		return null;
	}


	public static void main(String[] args) {
		AccountRepository repository= new AccountRepositoryJDBC();

		LOGGER.error(repository.findByAccountNumber(569741360));

}




	@Override
	public boolean depositToBalance(long accountNumber, long amount) {
LOGGER.trace("Entering update balance method with parameters: "+accountNumber+","+amount);
		
		//Account account=findByAccountNumber(accountNumber);
		try(Connection connection=ConnectionUtil.getConnection()){
			int parameterIndex= 0;
			String sql=	"UPDATE ACCOUNT SET A_BALANCE = ?+A_BALANCE  WHERE A_ACCOUNT_NUMBER=?";
		
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setLong(++parameterIndex, amount);
			statement.setLong(++parameterIndex, accountNumber);


			if(statement.executeUpdate()>0) {
				return true;
			}
		}catch(SQLException e) {
			LOGGER.error("Could not update balance on account.", e);
		}
		return false;

	}

	@Override
	public boolean withdrawFromBalance(long accountNumber, long amount) {
LOGGER.trace("Entering update balance method with parameters: "+accountNumber+","+amount);
		
		//Account account=findByAccountNumber(accountNumber);
		try(Connection connection=ConnectionUtil.getConnection()){
			int parameterIndex= 0;
			String sql=	"UPDATE ACCOUNT SET A_BALANCE = A_BALANCE-?  WHERE A_ACCOUNT_NUMBER=?";
		
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setLong(++parameterIndex, amount);
			statement.setLong(++parameterIndex, accountNumber);


			if(statement.executeUpdate()>0) {
				return true;
			}
		}catch(SQLException e) {
			LOGGER.error("Could not update balance on account.", e);
		}
		return false;

	}
	
}
