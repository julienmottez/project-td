package fr.treeptik.service;

public class ReverseEncryptionService implements EncryptionService{

	@Override
	public String encrypt(String mdp) {
		return new StringBuilder(mdp).reverse().toString();
	}

}
