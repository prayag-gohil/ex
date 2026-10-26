package com.example.demo.service;

import com.example.demo.entity.Account;

public interface AccountService {
	
	Account transferFunds(int fromId, int toId, double amount);
	
	Account getAccount(int id);
	
	Account updateAccount(int id,String accountHolder);
	
	String deleteAccount(int id);

}
