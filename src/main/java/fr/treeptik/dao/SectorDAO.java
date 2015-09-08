package fr.treeptik.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.treeptik.entity.Sector;
@Repository
public interface SectorDAO extends JpaRepository<Sector, Integer> {
}