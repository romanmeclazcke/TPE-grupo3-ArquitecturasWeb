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

        Carrera c = new Carrera("Prueba actualizar inscripcion");
        //repository.getCarreraRepository().createCarrera(c);
        Estudiante e = new Estudiante("nazareno2","meclazcke",44,'m',45094432,"TANDIL");
        Estudiante e2 = new Estudiante("mateo","asdad",22,'m',45094432,"AZUL");
        repository.getEstudianteRepository().createEstudiante(e);
        //System.out.println(repository.getEstudianteRepository().getEstudiantesByEdad());
        //System.out.println(repository.getEstudianteRepository().getEstudianteByGenero('f'));

        repository.getCarreraRepository().createCarrera(c);
        repository.getInscripcionRepository().createInscripcion(e,c);
        repository.getInscripcionRepository().createInscripcion(e2,c);


        //metodo para graduar estudiante
        repository.getInscripcionRepository().graduarEstudiante(e,c);
        repository.getInscripcionRepository().graduarEstudiante(e2,c);
        //System.out.println(repository.getInscripcionRepository().getCarrerasOrderByInscriptos());
        //System.out.println(repository.getInscripcionRepository().getEstudiantesByCarreraAndCiudad("AZUL",d));

        System.out.println(repository.getCarreraRepository().getReporteCarreras());


    }
}
