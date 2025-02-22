package org.sid.safarent.dao.voitureDao;

import org.sid.safarent.bean.voitureBean.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureDao extends JpaRepository<Voiture, Long> {
    Voiture findByMatricule(String Matricule);
    List<Voiture> findByCouleur(String couleur);

    List<Voiture> findByBoitevitesse(String boiteVitesse);
    List<Voiture> findByCategorieVoitureLibelle(String libelle);

    List<Voiture> findByNomModele(String nom);

    List<Voiture> findByVille(String ville);
    int deleteByMatricule(String Matricule);
    List<Voiture> findByNomModeleAndCategorieVoitureLibelle(String model, String libelle);
}
