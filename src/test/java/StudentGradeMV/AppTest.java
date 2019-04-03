package StudentGradeMV;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import StudentGradeMV.Domain.Student;
import StudentGradeMV.Domain.TemaLab;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.StudentRepo;
import StudentGradeMV.Repository.MemoryRepository.TemaLabRepo;
import StudentGradeMV.Service.ServiceStudent;
import StudentGradeMV.Service.ServiceTeme;
import StudentGradeMV.Validator.StudentValidator;
import StudentGradeMV.Validator.TemaLabValidator;
import StudentGradeMV.Validator.ValidationException;
import org.junit.Test;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    private String dirPath = System.getProperty("user.dir");

    @Test
    public void shouldAnswerWithTrue()
    {

        assertTrue( true );
    }

    @Test
    /*
     * Adding valid student
     * Adding duplicate valid student
     *
     */
    public void addStudent() {
        StudentRepo rep = new StudentRepo(new StudentValidator(),dirPath + "\\data\\StudentiXML.xml",false);
        ServiceStudent srv = new ServiceStudent(rep);

        Student student1 = new Student("1","name",932,"Lemne@gmail.com","Prof");
        Student student3 = new Student("2","name1",933,"el@gmail.com","profg");
        Student student4 = new Student("3","",932,"ret@.com","fdf");
        Student student5 = new Student("4","fsdf",932,"rcom","fdf");
        Student student6 = new Student("5","fds",1932,"ret@com","fdf");
        Student student7 = new Student("6","fds",93209,"","");
        Student student8 = new Student("7","",932,"re.com","fdf");
        Student student9 = new Student("8","nameH",933,"el@.com","profg");

        //correct add
        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        } catch (ValidatorException e) {

        }
        assertEquals(1,srv.getSize());

        //add duplicate - don't add
        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        //add invalid name
        try{
            if (srv.find(student4.getId()) == null)
                srv.add(student4);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        //add invalid e-mail
        try{
            if (srv.find(student5.getId()) == null)
                srv.add(student5);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        //add invalid group
        try{
            if (srv.find(student6.getId()) == null)
                srv.add(student6);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        //add empty name and empty e-mail and invalid group
        try{
            if (srv.find(student7.getId()) == null)
                srv.add(student7);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        //add invalid e-mail
        try{
            if (srv.find(student8.getId()) == null)
                srv.add(student8);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        //anohter correct add
        try{
            if (srv.find(student9.getId()) == null)
                srv.add(student9);
        }catch (ValidatorException ignored){}
        assertEquals(2,srv.getSize());
    }

    @Test
    public void findStudent() throws ValidatorException {

        StudentRepo rep = new StudentRepo(new StudentValidator(),dirPath + "\\data\\StudentiXML.xml",false);
        ServiceStudent srv = new ServiceStudent(rep);

        srv.add(new Student("1","sgdfg",932,"e@.com","sdfdg"));
        srv.add(new Student("2","sgdfg",932,"e@.com","sdfdg"));

        assertNotNull(srv.find("1"));
        assertNull(srv.find("46546"));
    }

    @Test
    public void updateStudent() throws ValidatorException {
        StudentRepo rep = new StudentRepo(new StudentValidator(),dirPath + "\\data\\StudentiXML.xml",false);
        ServiceStudent srv = new ServiceStudent(rep);
        Student st = new Student("2","sdf",932,"sdf@.com","asdsdfsd");
        srv.add(new Student("1","sgdfg",932,"e@.com","sdfdg"));

        try {
            srv.mod(new Student("1","asd",932999,"e@.com","sdfds"));
        }catch (ValidationException | NumberFormatException ignored) {}
        assertEquals(932,srv.find("1").getGrupa());
        assertEquals("sdfdg",srv.find("1").getIndrumator());
        assertEquals("sgdfg",srv.find("1").getNume());

        try {
            srv.mod(new Student("1","asd",9320,"ecom","sdfds"));
        }catch (ValidationException | NumberFormatException ignored) {}
        assertEquals(932,srv.find("1").getGrupa());
        assertEquals("e@.com",srv.find("1").getEmail());

        try {
            srv.mod(new Student("1","asd",932,"ecom","sdfds"));
        }catch (ValidationException | NumberFormatException ignored) {}
        assertEquals("e@.com",srv.find("1").getEmail());

        try {
            srv.mod(new Student("1","asd",932,"e@.com","sdfd!s"));
        }catch (ValidationException | NumberFormatException ignored) {}
        assertEquals("sdfdg",srv.find("1").getIndrumator());

        try {
            srv.mod(new Student("1","asd",935,"easd@.com","sdfds"));
        }catch (ValidationException | NumberFormatException ignored) {}
        assertEquals(935,srv.find("1").getGrupa());
        assertEquals("sdfds",srv.find("1").getIndrumator());
        assertEquals("asd",srv.find("1").getNume());
        assertEquals("easd@.com",srv.find("1").getEmail());

        try {
            if (srv.find("2") != null)
                srv.mod(st);
        }catch (ValidationException | NumberFormatException ignored) {}
        assertEquals("asd",srv.find("1").getNume());
    }

    @Test
    public void deleteStudent() throws ValidatorException {
        StudentRepo rep = new StudentRepo(new StudentValidator(),dirPath + "\\data\\StudentiXML.xml",false);
        ServiceStudent srv = new ServiceStudent(rep);
        srv.add(new Student("1","sgdfg",932,"e@.com","sdfdg"));
        srv.add(new Student("2","dasd",932,"asd.@c","asd"));
        srv.add(new Student("3","dad",933,"asd@.com","asda"));
        srv.add(new Student("4","dasdasd",931,"asd@.c","asdf"));

        assertEquals(4, srv.getSize());
        srv.del("1");
        assertEquals(3, srv.getSize());
        srv.del("567");
        assertEquals(3,srv.getSize());
        srv.del("4");
        assertEquals(2,srv.getSize());
    }

    @Test
    public void addTheme() {
        TemaLabRepo repo = new TemaLabRepo(new TemaLabValidator(),dirPath + "\\data\\TemaLaboratorXML.xml",false);
        ServiceTeme srv = new ServiceTeme(repo);

        TemaLab teme= new TemaLab(123,"fgdfgd",12,12);
        TemaLab teme1 = new TemaLab(1,"dfgfdgfdg",16,16);
        TemaLab teme2 = new TemaLab(-1,"dfgfdgfdg",16,16);
        TemaLab teme3 = new TemaLab(13 ,"dfgfdgfdg",0,11);
        TemaLab teme4 = new TemaLab(14 ,"dfgfdgfdg",0,16);
        TemaLab teme5 = new TemaLab(18 ,"dfgfdgfdg",6,1);
        TemaLab teme6 = new TemaLab(20 ,"dfgfdgfdg",0,16);
        TemaLab teme7 = new TemaLab(1 ,"dfgfdgfdg",5,12);

        try{
            if (srv.find(teme.getId()) == null)
                srv.add(teme);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme1.getId()) == null)
                srv.add(teme1);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme2.getId()) == null)
                srv.add(teme2);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme3.getId()) == null)
                srv.add(teme3);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme4.getId()) == null)
                srv.add(teme4);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme5.getId()) == null)
                srv.add(teme5);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme6.getId()) == null)
                srv.add(teme6);
        }catch (ValidatorException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme7.getId()) == null)
                srv.add(teme7);
        }catch (ValidatorException ignored){}
        assertEquals(2,srv.getSize());

        try{
            if (srv.find(teme7.getId()) == null)
                srv.add(teme7);
        }catch (ValidatorException ignored){}
        assertEquals(2,srv.getSize());
    }
}