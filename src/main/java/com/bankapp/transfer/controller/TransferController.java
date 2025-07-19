package com.bankapp.transfer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.bankapp.transfer.dto.TransferRequestDTO;
import com.bankapp.transfer.dto.TransferResponseDTO;
import com.bankapp.transfer.entity.Transfer;
import com.bankapp.transfer.service.TransferService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

	private final TransferService transferService;
	
	@PostMapping
	public ResponseEntity<Transfer> createTransfer(@RequestBody @Valid TransferRequestDTO dto){
		
		Transfer transfer = transferService.createTransfer(dto);
		return ResponseEntity.ok(transfer);
	}
	
	@GetMapping
	public ResponseEntity<List<TransferResponseDTO>> getAllTransfers(){
		List<TransferResponseDTO> transferList = transferService.getAllTransfers(); // servis icindeki metogu cagır sonra verileri
		return ResponseEntity.ok(transferList);                                     //  TransferResponseDTO nesnesine dönüstür
		                                                                             //dto listesini transferList degiskenine at
	}
	// HTTP GET isteği ile /api/transfers/{id} yoluna gelen isteği yakalar
	@GetMapping("/{id}") 
	public ResponseEntity<TransferResponseDTO> getTransferById(@PathVariable Long id) {// URL içindeki {id} parametresini metot parametresine bağlar

	    // Service katmanından id’ye göre transfer bilgisi alınır
	    TransferResponseDTO transfer = transferService.getTransferById(id);

	    // HTTP 200 OK ve transfer verisi JSON olarak döndürülür
	    return ResponseEntity.ok(transfer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransferResponseDTO> updateTransfer(  @PathVariable Long id,  @RequestBody @Valid TransferRequestDTO dto) {
	     // Servisten güncellenmiş transfer verisini alır
	    TransferResponseDTO updatedTransfer = transferService.updateTransfer(id, dto);

	    // HTTP 200 OK ile güncellenen veriyi döner
	    return ResponseEntity.ok(updatedTransfer);
   
	}
	// ID parametresi ile transfer kaydını silmek için endpoint
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
	    // Servise silme işlemi yaptırılır
	    transferService.deleteTransfer(id);

	    // Başarılı silme sonrası HTTP 204 No Content döner (gövdesiz)
	    return ResponseEntity.noContent().build();
	}

}
