package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Prize;
import projet.m2.back.repository.PrizeRepository;
import projet.m2.back.service.interfaces.IPrizeService;
import projet.m2.back.service.interfaces.IUtils;

import javax.transaction.Transactional;

@Service
public class PrizeServiceImpl implements IPrizeService {

    @Autowired
    PrizeRepository repo;

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
}
