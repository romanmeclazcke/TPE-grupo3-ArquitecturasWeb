import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;
import Repository.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.plaf.synth.SynthTextAreaUI;

public class main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();

        Factory.abstractFactory repository = Factory.abstractFactory.getInstance(1,em);

        Carrera c = new Carrera("TUDAI");
        //repository.getCarreraRepository().createCarrera(c);
        Estudiante e = new Estudiante("roman","meclazcke",22,'m',46094666,"TANDIL");
        repository.getEstudianteRepository().createEstudiante(e);
       // System.out.println(repository.getEstudianteRepository().getEstudiantesByEdad());
       // System.out.println(repository.getEstudianteRepository().getEstudianteByGenero('f'));

        //repository.getCarreraRepository().createCarrera(c);

        repository.getInscripcionRepository().createInscripcion(e,c);



    }
}
