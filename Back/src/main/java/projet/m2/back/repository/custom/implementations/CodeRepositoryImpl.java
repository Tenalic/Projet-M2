package projet.m2.back.repository.custom.implementations;

import projet.m2.back.entity.Code;
import projet.m2.back.repository.custom.interfaces.CodeRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CodeRepositoryImpl implements CodeRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public int changeUsed(long id, boolean used) {
        Code code = em.find(Code.class, id);
        code.setUsed(used);
        em.merge(code);
        return 0;
    }

    @Override
    public int updateCode(Code code) {
        em.merge(code);
        return 0;
    }
}
