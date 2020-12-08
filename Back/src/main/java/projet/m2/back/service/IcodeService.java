package projet.m2.back.service;

public interface IcodeService {

    int useCode(final String idAccount, final String code);

    int changeUsed(final long id, final boolean used);
}
