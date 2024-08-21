package com.emrsys.medmatrix.util;

import org.springframework.boot.CommandLineRunner;

import com.emrsys.medmatrix.service.PswEncryptionService;

public class PswEncryptionRunner implements CommandLineRunner{
	private final PswEncryptionService pswEncryptionService;

    public PswEncryptionRunner(PswEncryptionService pswEncryptionService) {
        this.pswEncryptionService = pswEncryptionService;
    }

    @Override
    public void run(String... args) throws Exception {
        pswEncryptionService.encryptAndSavePasswords();
        System.out.println("Password encryption completed!");
    }
}
