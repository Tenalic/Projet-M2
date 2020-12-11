package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;
import projet.m2.back.repository.PrizeRepository;
import projet.m2.back.repository.custom.implementations.PrizeRepositoryImpl;
import projet.m2.back.repository.custom.interfaces.PrizeRepositoryCustom;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.IPrizeService;
import projet.m2.back.service.interfaces.IUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class PrizeServiceImpl implements IPrizeService {

    @Autowired
    PrizeRepository repo;

    @Autowired
    IAccountService iAccountService;

    @Autowired
    IUtils utils;

    @Transactional
    public void createPrize(String reward, int quantity, int weight) {
        repo.save(new Prize(reward, quantity, weight));
    }

    @Override
    public Iterable<Prize> getAllPrize() {
        return repo.findAll();
    }

    /**
     * Fonction qui renvoie un gain selon une probabilité ( 60% juste un dé, 10% un dé + 50 points,
     * 10% un dé + 100 points, 10% un dé + 150 points, 10% un dé et un prix)
     * @return 0 si juste dé, 1 si 50 pts, 2 pour 100 pts, 3 pour 150 pts et 4 pour un prix
     */
    @Override
    public Integer gain() {
        int rand = utils.rand(0, 100);
        if (rand <= 60)   //gain dé uniquement
        {
            return 0;
        } else {
            if ((rand <= 90))  //gain dé + argent
            {
                if (70 >= rand) {
                    return 1;
                } else {
                    if (80 >= rand) {
                        return 2;
                    } else {
                        return 3;
                    }
                }
            } else {
                return 4;
            }
        }
    }

    /**
     * Fonction qui crée un jeu de données pour les prix dans la base de données
     */
    @Override
    @Transactional
    public void createDatasetPrize() {
        Prize p = new Prize("prize1",100 , 100);
        repo.save(p);
        p = new Prize("prize2",100, 100 );
        repo.save(p);
        p = new Prize("prize3",70, 80 );
        repo.save(p);
        p = new Prize("prize4",50, 40 );
        repo.save(p);
        p = new Prize("prize5",40, 30 );
        repo.save(p);
        p = new Prize("prize6",40, 20 );
        repo.save(p);
        p = new Prize("prize7",30, 20 );
        repo.save(p);
        p = new Prize("prize8",4, 5 );
        repo.save(p);
    }


    /**
     * Fonction qui permet de tirer aléatoirement un prix.
     * @param account
     * @return le prix qui à été choisi aléatoirement.
     */
    @Override
    @Transactional
    public String randomPrize (Account account)  //choice comporte le resultat de gain
    {
            Random r = new Random();
            //account.setNbDice(account.getNbDice() + 1);
            Iterable<Prize> tmp = getAllPrize(); //tout les prize
            ArrayList<Prize> listPrize = new ArrayList<>();//convertir en ArrayList que ceux qui ont de la quantité
            for(Prize p : tmp)
            {
                if(p.getQuantity()>0)
                {
                    listPrize.add(p);
                }
            }
            int tmpRand = r.nextInt(listPrize.size());
            Prize prize = listPrize.get(tmpRand);
            //décrémenter la quantité
            prize.setQuantity(prize.getQuantity() - 1);
            //update le prize dans la bdd
            repo.updatePrize(prize.getId(), prize.getQuantity());
            account.getPrize().add(prize.getReward());
            return prize.getReward();
    }

}
