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
        Estudiante mai = new Estudiante("Maia","Manze",19,'F',46364081,"Tandil");
        Estudiante t = new Estudiante("Tincho","Acosta",20,'F',46364081,"Tandil");
        //es.createEstudiante(t);
        //System.out.println("User Created " + es.createEstudiante(e));
        //System.out.println(es.getEstudiantesByEdad());
        System.out.println(es.getEstudianteByGenero('f'));

        //System.out.println(es.getEstudianteByLibreta(4));
    }
}
