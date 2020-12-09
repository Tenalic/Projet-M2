package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;
import projet.m2.back.repository.PrizeRepository;
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
    IUtils utils;

    @Transactional
    public void createPrize(String reward, int quantity) {
        repo.save(new Prize(reward, quantity));
    }

    @Override
    public Iterable<Prize> getAllPrize() {
        return repo.findAll();
    }

    /**
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
               /* Iterable<Prize> tmp = getAllPrize();
                ArrayList<Prize> listPrize = new ArrayList<Prize>();
                for(Prize p : tmp)
                {
                    listPrize.add(p);
                }
                int tmpRand = r.nextInt(listPrize.size());
                listPrize.get(tmpRand);//donne le cadeau obtenu*/
                return 4;
            }
        }
    }

    @Override
    public void createDatasetPrize() {
        Prize p = new Prize("prize1",100 );
        repo.save(p);
        p = new Prize("prize2",100 );
        repo.save(p);
        p = new Prize("prize3",70 );
        repo.save(p);
        p = new Prize("prize4",50 );
        repo.save(p);
        p = new Prize("prize5",40 );
        repo.save(p);
        p = new Prize("prize6",40 );
        repo.save(p);
        p = new Prize("prize7",30 );
        repo.save(p);
        p = new Prize("prize8",4 );
        repo.save(p);
    }


    public void randomPrize (int choice, Account account)  //choice comporte le resultat de gain
    {
        if(choice == 0)//gagne juste un dé
        {
            account.setNbDice(account.getNbDice() + 1);
        }
        if (choice == 1) //gagne un dé + 50pts
        {
            account.setNbDice(account.getNbDice() + 1);
            account.setCredit(account.getCredit() + 50);
        }
        if (choice == 2) // gagne un dé + 100 pts
        {
            account.setNbDice(account.getNbDice() + 1);
            account.setCredit(account.getCredit() + 100);
        }
        if (choice == 3)  // gagne un dé + 150pts
        {
            account.setNbDice(account.getNbDice() + 1);
            account.setCredit(account.getCredit() + 150);
        }
        if (choice == 4)  //donne un dé et un prix aléatoire au compte
        {
            Random r = new Random();
            account.setNbDice(account.getNbDice() + 1);
            Iterable<Prize> tmp = getAllPrize();
            ArrayList<Prize> listPrize = new ArrayList<>();
            for(Prize p : tmp)
            {
                listPrize.add(p);
            }
            int tmpRand = r.nextInt(listPrize.size());
            Collection<Prize> prize = account.getPrize();
            prize.add(listPrize.get(tmpRand));
            //delete le prix de la bdd si il y en a qu'un
            if(listPrize.get(tmpRand).getQuantity() == 1)
            {
                repo.delete(listPrize.get(tmpRand));
            }
            else
            {
                //décrementer la quantité
                listPrize.get(tmpRand).setQuantity(listPrize.get(tmpRand).getQuantity() - 1);
                //update le prize dans la bdd

            }

        }
    }

}
