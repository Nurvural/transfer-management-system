package com.bankapp.transfer.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {

	    private Long id;
	    private String senderIban;
	    private String receiverIban;
	    private Double amount;
	    private String description;
	    private LocalDateTime date;
}
