package org.sid.safarent.service.impl.voitureServiceImpl;

import org.sid.safarent.bean.voitureBean.AgenceLocation;
import org.sid.safarent.dao.voitureDao.AgenceLocationDao;
import org.sid.safarent.security.bean.AppRole;
import org.sid.safarent.security.dao.AppRoleDao;
import org.sid.safarent.service.facade.voitureService.AgenceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class AgenceLocationServiceImpl implements AgenceLocationService {
    @Autowired
    private AgenceLocationDao agenceLocationDao;
    @Autowired
    private AgenceLocationService agenceLocationService;
    @Autowired
    private AppRoleDao appRoleDao;


    @Override
    public int save(AgenceLocation agenceLocation) {
        if (agenceLocation == null) {
            System.out.println("agence est null");
            return -1;
        } else if (agenceLocationDao.findByIceAgLoc(agenceLocation.getIceAgLoc()) != null) {
            System.out.println("deja existed");
            return -2;
        } else {
            String pw=agenceLocation.getPassword();
            if (pw.isEmpty()) {
                return -3;
            }


            agenceLocation.setPassword(pw);

            AppRole appRole1=appRoleDao.findByRoleName("MANAGER-VOI");
            AppRole appRole2=appRoleDao.findByRoleName("USER");

            agenceLocation.getAppRoles().add(appRole1);
            agenceLocation.getAppRoles().add(appRole2);

            agenceLocationDao.save(agenceLocation);
            return 1;
        }
    }





    @Override
    public AgenceLocation findByiceAgLoc(Long iceAgLoc) {
        return agenceLocationDao.findByIceAgLoc(iceAgLoc);
    }

    @Override
    public List<AgenceLocation> findAll() {
        return agenceLocationDao.findAll();
    }

    @Override
    public int update(AgenceLocation agenceLocation) {
        if (agenceLocation == null) {
            return -1;
        }

        Long iceAgLoc = agenceLocation.getIceAgLoc();
        if (iceAgLoc == null) {
            return -2;
        }

        AgenceLocation existingagenceLocation = agenceLocationDao.findByIceAgLoc(iceAgLoc);
        if (existingagenceLocation == null) {
            return -3;
        }

        existingagenceLocation.setAdresse(agenceLocation.getAdresse());
        existingagenceLocation.setClients(agenceLocation.getClients());
        existingagenceLocation.setPaiements(agenceLocation.getPaiements());
        existingagenceLocation.setNumCompteBkAgLoc(agenceLocation.getNumCompteBkAgLoc());
        existingagenceLocation.setNumTelephone(agenceLocation.getNumTelephone());
        existingagenceLocation.setRaisonSocialAg(agenceLocation.getRaisonSocialAg());
        existingagenceLocation.setRibAgenceLoc(agenceLocation.getRibAgenceLoc());
        existingagenceLocation.setRCAgLoc(agenceLocation.getRCAgLoc());
        existingagenceLocation.setUsername(agenceLocation.getUsername());
        existingagenceLocation.setVoitures(agenceLocation.getVoitures());
        if(agenceLocation.getImagePaths()!=null && !agenceLocation.getImagePaths().isEmpty()){
            existingagenceLocation.setImagePaths(agenceLocation.getImagePaths());
        }else {
            existingagenceLocation.setImagePaths(null);
        }

        agenceLocationDao.save(existingagenceLocation);
        return 1;

    }

    @Override
    public int deleteByIceAgLoc(Long iceAgLoc) {
        return agenceLocationDao.deleteByIceAgLoc(iceAgLoc);
    }


    @Override
    public AgenceLocation findByUsername(String username) {
        return agenceLocationDao.findByUsername(username);
    }

}


