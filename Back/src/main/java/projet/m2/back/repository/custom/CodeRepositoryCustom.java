package projet.m2.back.repository.custom;

import projet.m2.back.entity.Code;

public interface CodeRepositoryCustom {

    int changeUsed(final long id, final boolean used);

    int updateCode(Code code);
}
