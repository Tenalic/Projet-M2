package projet.m2.back.service.interfaces;

public interface ICodeService {

    /**
     * @param idAccount : id du compte ou l'on souhaite appliqué le code
     * @param code      : code du code
     * @return 0 : ok, 1 : compte non trouvé, 2 : code deja utilisé, 3 : code inconue
     */
    Object useCode(final long idAccount, final String code);

    int changeUsed(final long id, final boolean used);

    void createDatasetCode();
}
