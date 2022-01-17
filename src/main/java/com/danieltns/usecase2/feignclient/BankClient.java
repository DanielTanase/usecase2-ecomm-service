package com.danieltns.usecase2.feignclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.danieltns.usecase2.dto.TransactionDto;
import com.danieltns.usecase2.dto.TransferRequestDto;
import com.danieltns.usecase2.utils.AppConstants;

@FeignClient(name = AppConstants.BANK_SERVICE_URL + AppConstants.TRANSACTION_CONTROLLER)
public interface BankClient {
	
	@PostMapping(AppConstants.TRANSFER_FUND)
	public TransactionDto transferFund(@Valid @RequestBody TransferRequestDto transferDto);
}
