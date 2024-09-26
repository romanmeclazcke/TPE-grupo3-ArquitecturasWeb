package Factory;

import Repository.InscripcionRepositoryImpl;

import javax.persistence.EntityManager;

public abstract class abstractFactory {
    public static abstractFactory getInstance(int whichFactory, EntityManager em) {
        switch (whichFactory) {
            case 1:
                return MySqlRepository.getInstancia(em);
            //case 2:
            //  return new DerbyDaoFactory();
            default:
                return null;
        }
    }

    public abstract Repository.CarreraRepositoryImpl getCarreraRepository();
    public abstract Repository.EstudianteRepositoryImpl getEstudianteRepository();
    public abstract Repository.InscripcionRepositoryImpl getInscripcionRepository();
}