package StudentGradeMV;

import StudentGradeMV.Domain.Nota;
import StudentGradeMV.Domain.Student;
import StudentGradeMV.Domain.TemaLab;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.NotaRepo;
import StudentGradeMV.Service.ServiceNote;
import StudentGradeMV.Validator.NotaValidator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class AddGrade
{
    private String dirPath = System.getProperty("user.dir");
    private NotaRepo rep = new NotaRepo(new NotaValidator());
    private ServiceNote srv = new ServiceNote(rep);

    @Test
    public void addNota()
    {
        TemaLab tema1 = new TemaLab(1, "abc", 2, 5);
        TemaLab tema2 = new TemaLab(2, "abcd", 5, 7);
        Student student1 = new Student("1", "Dragos", 932, "dragos@yahoo.com", "Coroiu");
        Student student2 = new Student("2", "Alex", 932, "alex@yahoo.com", "Radu");
        Nota nota1 = new Nota(1, "1", 1, 10, LocalDateTime.of(2018,10,10,10,10));
        Nota nota2 = new Nota(2, "2", 2, 10, LocalDateTime.of(2018,11,11,11,11));

        assertEquals(0, srv.getSize());
        try{
            srv.add(nota1);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1, srv.getSize());

        try {
            srv.add(nota2);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(2, srv.getSize());
    }
}
