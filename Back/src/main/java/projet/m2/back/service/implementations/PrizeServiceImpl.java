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
    public void createPrize(String reward) {
        repo.save(new Prize(reward));
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
}
