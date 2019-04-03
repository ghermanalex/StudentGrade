package StudentGradeMV;

import StudentGradeMV.Domain.TemaLab;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.TemaLabRepo;
import StudentGradeMV.Service.ServiceTeme;
import StudentGradeMV.Validator.TemaLabValidator;
import StudentGradeMV.Validator.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AddAssignment {

    private String dirPath = System.getProperty("user.dir");
    private TemaLabRepo rep = new TemaLabRepo(new TemaLabValidator(),dirPath + "\\data\\TemaLaboratorXML.xml",false);
    private ServiceTeme srv = new ServiceTeme(rep);

    @Test
    public void addAssignment()
    {
        TemaLab tema1 = new TemaLab(1, "abcd", 2, 2);
        TemaLab tema2 = new TemaLab(2, "bcdf", 5, 4);

        assertEquals(0, srv.getSize());
        try{
            if (srv.find(tema1.getId()) == null)
                srv.add(tema1);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1, srv.getSize());
        try{
            if (srv.find(tema2.getId()) == null)
                srv.add(tema2);
        } catch( ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1, srv.getSize());
    }

    @Test
    public void checkId()
    {
        TemaLab tema1 = new TemaLab(null, "abcd", 2, 2);
        TemaLab tema2 = new TemaLab(-1, "bcdf", 5, 4);
        TemaLab tema3 = new TemaLab(3, "fokasdkf", 9,10);

        assertEquals(0,srv.getSize());
        try{
            if (srv.find(tema1.getId()) == null)
                srv.add(tema1);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(0,srv.getSize());
        try{
            if (srv.find(tema2.getId()) == null)
                srv.add(tema2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(0,srv.getSize());
        try{
            if (srv.find(tema3.getId()) == null)
                srv.add(tema3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkDescription()
    {

    }

}
