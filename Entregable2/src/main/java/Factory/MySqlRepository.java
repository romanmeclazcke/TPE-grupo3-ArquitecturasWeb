package Factory;

import Repository.InscripcionRepositoryImpl;

import javax.persistence.EntityManager;

public class MySqlRepository extends abstractFactory{
    private EntityManager em;
    private static MySqlRepository instance;

    public static Factory.MySqlRepository getInstancia(EntityManager em){
        if(instance == null){
            instance = new MySqlRepository(em);
        }
        return instance;
    }

    private MySqlRepository(EntityManager em){
       this.em = em;
    }

    @Override
    public Repository.CarreraRepositoryImpl getCarreraRepository() {
        return Repository.CarreraRepositoryImpl.getInstancia(this.em);
    }

    @Override
    public Repository.EstudianteRepositoryImpl getEstudianteRepository() {
        return Repository.EstudianteRepositoryImpl.getInstancia(this.em);
    }

    @Override
    public InscripcionRepositoryImpl getInscripcionRepository() {
        return InscripcionRepositoryImpl.getInstancia(this.em);
    }
}