package fr.treeptik.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.treeptik.entity.TypeDistributor;

@Service
@Scope(value = "singleton")
public class TypeDistributorService extends AbstractService<TypeDistributor, Integer> {

	public TypeDistributorService() {
		super(TypeDistributor.class);
	}
}
