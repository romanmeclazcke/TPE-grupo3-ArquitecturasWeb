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

        Carrera c1 = new Carrera("TUDAI");
        repository.getCarreraRepository().createCarrera(c1);

        Carrera c2 = new Carrera("Historia");
        repository.getCarreraRepository().createCarrera(c2);

        Estudiante e = new Estudiante("Maia2", "Manze", 20, 'f', 46291832, "Olavarría");
        Estudiante e2 = new Estudiante("María", "Lopez", 18, 'f', 46364081, "Rauch");
        repository.getEstudianteRepository().createEstudiante(e2);
        //System.out.println(repository.getEstudianteRepository().getEstudiantesByEdad());
        //System.out.println(repository.getEstudianteRepository().getEstudianteByGenero('f'));

        repository.getInscripcionRepository().createInscripcion(e,c1);
        repository.getInscripcionRepository().createInscripcion(e2,c1);

        repository.getInscripcionRepository().createInscripcion(e,c2);

        //metodo para graduar estudiante
        repository.getInscripcionRepository().graduarEstudiante(e2,c1);

        //System.out.println(repository.getInscripcionRepository().getCarrerasOrderByInscriptos());
        //System.out.println(repository.getInscripcionRepository().getEstudiantesByCarreraAndCiudad("AZUL",d));

        System.out.println(repository.getCarreraRepository().getReporteCarreras());


    }
}
