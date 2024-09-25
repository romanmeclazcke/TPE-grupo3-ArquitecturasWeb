import Entities.Estudiante;
import Repository.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();

        EstudianteRepositoryImpl es = new EstudianteRepositoryImpl(em);

        Estudiante e = new Estudiante("roman","meclazcke",22,'m',46094663,"tandil");
        System.out.println("User Creaated " + es.createEstudiante(e));
        System.out.println(es.getEstudiantesByEdad());
    }
}
