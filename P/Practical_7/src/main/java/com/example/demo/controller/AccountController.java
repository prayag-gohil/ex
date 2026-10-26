package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountUpdateRequest;
import com.example.demo.dto.TransferRequest;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	//Transfer money
	@PostMapping("/transfer")
	public ResponseEntity<Account> transferFunds(@RequestBody TransferRequest transferRequest)
	{
		Account account = accountService.transferFunds(transferRequest.getFromId(),transferRequest.getToId(),
				transferRequest.getAmount());
		return ResponseEntity.ok(account);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable int id)
	{
		Account account = accountService.getAccount(id);
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable int id,@RequestBody AccountUpdateRequest request)
	{
		Account account = accountService.updateAccount(id, request.getAccountHolder());
		return ResponseEntity.ok(account);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id)
	{
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	}
	
}
