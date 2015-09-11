package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Rack;

@Repository
public interface RackDAO extends JpaRepository<Rack, Integer> {

	java.util.List<Rack> findByTypeRack_Id(Integer id);
	
//	@Query("Select r from Rack r join r.typeRack t where t.id=:idTypeRack")
//	List<Rack> findByTypeRackId(@org.springframework.data.repository.query.Param("idTypeRack") Integer idTypeRack);

}
