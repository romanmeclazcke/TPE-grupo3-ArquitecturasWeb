package Repository;

import DTO.EstudianteDTO;
import Entities.Estudiante;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    private final EntityManager em;
    private static  EstudianteRepositoryImpl instancia;

    private EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public EstudianteDTO createEstudiante(Estudiante e) {
        this.em.getTransaction().begin();
        if (e.getNum_libreta() == null) {
            this.em.persist(e);
        } else {
            e = this.em.merge(e);
        }
        this.em.getTransaction().commit();

        return new EstudianteDTO(e.getNum_libreta(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getGenero(), e.getCiudad_residencia());
    }

    @Override
    public List<EstudianteDTO> getEstudiantesByEdad() { //CONSULTARRRRRRRRRRRRRRRRRRRRRRRRR
        List<EstudianteDTO> estudiantes = em.createQuery(
                "SELECT new DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.edad) FROM Estudiante e ORDER BY e.edad"
                    , EstudianteDTO.class).getResultList();
        return estudiantes;
    }

    @Override
    public EstudianteDTO getEstudianteByLibreta(int numero_libreta) {
        return em.find(EstudianteDTO.class, numero_libreta);
    }

    @Override

    public List<EstudianteDTO> getEstudianteByGenero(char genero) {
        String query = "SELECT new DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.genero) FROM Estudiante e WHERE e.genero = :genero";
        List<EstudianteDTO> estudiantes = em.createQuery(query, EstudianteDTO.class).setParameter("genero", genero).getResultList();
        return estudiantes;
    }

    public static EstudianteRepositoryImpl getInstancia(EntityManager em){
        if(instancia == null){
            instancia = new EstudianteRepositoryImpl(em);
        }
        return instancia;
    }
}
