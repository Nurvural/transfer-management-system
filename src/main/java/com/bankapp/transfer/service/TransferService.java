package com.bankapp.transfer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bankapp.transfer.dto.TransferRequestDTO;
import com.bankapp.transfer.dto.TransferResponseDTO;
import com.bankapp.transfer.entity.Transfer;
import com.bankapp.transfer.exception.InsufficientBalanceException;
import com.bankapp.transfer.exception.ResourceNotFoundException;
import com.bankapp.transfer.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {
	
	private final TransferRepository transferRepository;

	private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

	public Transfer createTransfer(TransferRequestDTO dto) {
		logger.info("Transfer işlemi başladı: sender={}, receiver={}, amount={}", dto.getSenderIban(), dto.getReceiverIban(), dto.getAmount());

		if(dto.getAmount() > 10000) {
			logger.warn("Bakiye yetersiz: amount={}", dto.getAmount());
			throw new InsufficientBalanceException("Bakiye yetersiz, transfer yapılamaz.");
		}

		Transfer transfer = Transfer.builder()
				.senderIban(dto.getSenderIban())
				.receiverIban(dto.getReceiverIban())
				.amount(dto.getAmount())
				.description(dto.getDescription())
				.date(LocalDateTime.now())
				.build();

		transfer = transferRepository.save(transfer);

		logger.info("Transfer işlemi başarılı: id={}", transfer.getId());

		return transfer;
		
		// cıktı: Transfer işlemi başladı: sender=TR123, receiver=TR456, amount=1000

	}
	public List<TransferResponseDTO> getAllTransfers() {
	    List<Transfer> transferEntities = transferRepository.findAll(); //transfer kayıtlarını al islem bize List<Transfer> olarak döner
	    return transferEntities.stream() // listedeki her elemana islem yapar.
	            .map(transfer -> new TransferResponseDTO( //listedeki her transfer nesnesini alir. onun yerine TransferResponseDTO nesmesi olusturur
	                    transfer.getId(),                  //Her bir transfer nesnesini alıyor ve onun içindeki verileri DTO nesnesine dönüştürüyor.
	                    transfer.getSenderIban(),
	                    transfer.getReceiverIban(),
	                    transfer.getAmount(),
	                    transfer.getDescription(),
	                    transfer.getDate()
	            ))
	            .collect(Collectors.toList()); //TransferResponseDTO  nesnelerini liste haline geitr.
	}
	  // ID’ye göre transfer sorgulama metodu
    public TransferResponseDTO getTransferById(Long id) {
        // Veritabanından id’ye göre transfer bulmaya çalışır
        Transfer transfer = transferRepository.findById(id)
            // Eğer bulunamazsa özel exception fırlatır
        		 .orElseThrow(() -> new ResourceNotFoundException("Transfer bulunamadı, id: " + id));
        // Bulunan Transfer entity’sini DTO’ya dönüştürür ve döner
        return new TransferResponseDTO(
            transfer.getId(),
            transfer.getSenderIban(),
            transfer.getReceiverIban(),
            transfer.getAmount(),
            transfer.getDescription(),
            transfer.getDate()
        );
    }
    public TransferResponseDTO updateTransfer(Long id, TransferRequestDTO dto) {
        // Veritabanından transferi bulmaya çalışır
        Transfer existingTransfer = transferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transfer bulunamadı, id: " + id));

        // İş kurallarını kontrol et (örnek: miktar sınırı)
        if(dto.getAmount() > 10000) {
            throw new InsufficientBalanceException("Bakiye yetersiz, transfer güncellenemez.");
        }

        // Var olan entity üzerinde güncellemeleri uygula
        existingTransfer.setSenderIban(dto.getSenderIban());
        existingTransfer.setReceiverIban(dto.getReceiverIban());
        existingTransfer.setAmount(dto.getAmount());
        existingTransfer.setDescription(dto.getDescription());
        
        // Güncellenmiş entity’yi kaydet
        Transfer updatedTransfer = transferRepository.save(existingTransfer);

        // Entity’yi DTO’ya dönüştürerek döndür
        return new TransferResponseDTO(
                updatedTransfer.getId(),
                updatedTransfer.getSenderIban(),
                updatedTransfer.getReceiverIban(),
                updatedTransfer.getAmount(),
                updatedTransfer.getDescription(),
                updatedTransfer.getDate()
            );
    }
    public void deleteTransfer(Long id) {
        // Veritabanında transferi arar
        Transfer transfer = transferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transfer bulunamadı, id: " + id));

        // Kayıt bulunursa siler
        transferRepository.delete(transfer);
    }

	
	
}
