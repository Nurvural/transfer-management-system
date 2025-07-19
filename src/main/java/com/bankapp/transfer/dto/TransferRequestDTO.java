package com.bankapp.transfer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
	
       @NotBlank(message = "Sender IBAN boş olamaz") //string degiskenler için hem null olamaz hem de bosluk/bos iceremez
       @Size(min = 15, max = 34, message = "Sender IBAN 15-34 karakter arasında olmalı")
	   private String senderIban;
       
       @NotBlank(message = "Receiver IBAN boş olamaz")
       @Size(min = 15, max = 34, message = "Receiver IBAN 15-34 karakter arasında olmalı")
	    private String receiverIban;
       
       @NotNull(message = "Amount (tutar) boş olamaz") //String, Integer vs. null olmaz bosluk vs olabişlir
       @Positive(message = "Amount pozitif bir sayı olmalı")
	    private Double amount;
	    private String description;

}
