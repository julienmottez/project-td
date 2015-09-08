package fr.treeptik.service;

import org.springframework.stereotype.Component;

@Component
public class ReverseEncryptionService implements EncryptionService{

	@Override
	public String encrypt(String mdp) {
		return new StringBuilder(mdp).reverse().toString();
	}

}
