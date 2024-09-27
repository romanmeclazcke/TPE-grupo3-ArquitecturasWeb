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

        Carrera d = new Carrera("peperoni");
        //repository.getCarreraRepository().createCarrera(c);
        Estudiante e = new Estudiante("agostina","asdad",22,'m',45094432,"AZUL");
        Estudiante e2 = new Estudiante("mateo","asdad",22,'m',45094432,"AZUL");
        repository.getEstudianteRepository().createEstudiante(e);
        repository.getEstudianteRepository().createEstudiante(e2);
        //System.out.println(repository.getEstudianteRepository().getEstudiantesByEdad());
        //System.out.println(repository.getEstudianteRepository().getEstudianteByGenero('f'));

        repository.getCarreraRepository().createCarrera(d);



        repository.getInscripcionRepository().createInscripcion(e,d);
        repository.getInscripcionRepository().createInscripcion(e2,d);

        System.out.println(repository.getInscripcionRepository().getCarrerasOrderByInscriptos());


        //System.out.println(repository.getInscripcionRepository().getEstudiantesByCarreraAndCiudad("AZUL",d));

        System.out.println(repository.getCarreraRepository().getReporteCarreras());


    }
}
