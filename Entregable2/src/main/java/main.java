import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;
import Repository.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();

        Factory.abstractFactory repository = Factory.abstractFactory.getInstance(1,em);

        Carrera c = new Carrera("TUDAI");
        repository.getCarreraRepository().createCarrera(c);
        //System.out.println(repository.getEstudianteRepository().getEstudiantesByEdad());



    }
}
