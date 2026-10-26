package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}



	@Override
	@Transactional
	public Account transferFunds(int fromId, int toId, double amount)
	{
		
		//Get sender account
		Account fromAccount = accountRepository.findById(fromId).get();
		
		//check balance
		if(fromAccount.getBalance() < amount)
		{
			throw new InsufficientBalanceException("Insifficient Balance in account " + fromId);
		}
		
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		
		//Get receiver account
		Account toAccount = accountRepository.findById(toId).get();
		
		//Credit money to receiver
		toAccount.setBalance(toAccount.getBalance() + amount);
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		return fromAccount;
	}
	
	@Override
	public Account getAccount(int id)
	{
		return accountRepository.findById(id).get();
	}
	
	@Override
	public Account updateAccount(int id, String accountHolder)
	{
		Account existingAccount = accountRepository.findById(id).get();
		existingAccount.setAccountHolder(accountHolder);
		return accountRepository.save(existingAccount);
	}
	
	@Override
	public String deleteAccount(int id)
	{
		Account account = accountRepository.findById(id).get();
		accountRepository.delete(account);
		return null;
	}
}
