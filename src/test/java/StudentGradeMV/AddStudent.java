package StudentGradeMV;

import StudentGradeMV.Domain.Student;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.StudentRepo;
import StudentGradeMV.Service.ServiceStudent;
import StudentGradeMV.Validator.StudentValidator;
import StudentGradeMV.Validator.ValidationException;
import org.junit.Test;


import static org.junit.Assert.*;


public class AddStudent {
    private String dirPath = System.getProperty("user.dir");
    private StudentRepo rep = new StudentRepo(new StudentValidator(),dirPath + "\\data\\StudentiXML.xml",false);
    private ServiceStudent srv = new ServiceStudent(rep);

    @Test
    /*
     * Adding valid student
     * Adding duplicate valid student with different ID
     * Expected: Success
     */
    public void addStudent() {
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        Student student2 = new Student("2","name",932,"Lemne@.com","Prof");

        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(student2.getId()) == null)
                srv.add(student2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(2,srv.getSize());
    }

    @Test
    public void checkDuplicates(){
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkName(){
        Student student3 = new Student("2","name1",933,"el@.com","profg");
        Student student4 = new Student("2","name!.",933,"el@.com","profg");
        Student student5 = new Student("2","",933,"el@.com","profg");
        Student student6 = new Student("2","asd",933,"el@.com","profg");

        try{
            if (srv.find(student3.getId()) == null)
                srv.add(student3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student4.getId()) == null)
                srv.add(student4);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student5.getId()) == null)
                srv.add(student5);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student6.getId()) == null)
                srv.add(student6);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkEmail(){
        Student student1 = new Student("4","fsdf",932,"rcom","fdf");
        Student student2 = new Student("5","fds",932,"ret@.com","fdf");
        Student student3 = new Student("6","fds",933,"","ASD");
        Student student4 = new Student("7","",932,"re.com","fdf");

        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student2.getId()) == null)
                srv.add(student2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student3.getId()) == null)
                srv.add(student3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student4.getId()) == null)
                srv.add(student4);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkGroup(){
        Student student1 = new Student("4","fsdf",982,"rcom","fdf");
        Student student2 = new Student("5","fds",132,"ret@.com","fdf");
        Student student3 = new Student("6","fds",939,"","ASD");
        Student student4 = new Student("7","",932,"re.com","fdf");
        Student student5 = new Student("7","",93265,"re.com","fdf");
        Student student6 = new Student("7","",-1,"re.com","fdf");

        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student2.getId()) == null)
                srv.add(student2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student3.getId()) == null)
                srv.add(student3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student4.getId()) == null)
                srv.add(student4);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student5.getId()) == null)
                srv.add(student5);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student6.getId()) == null)
                srv.add(student6);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkProfessor(){
        Student student1 = new Student("2","nameas",933,"el@.com","profg");
        Student student2 = new Student("2","name",933,"el@.com","profg.lkf");
        Student student3 = new Student("2","ADSA",933,"el@.com","profg!@!#");
        Student student4 = new Student("2","asd",933,"el@.com","12fsd");

        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student2.getId()) == null)
                srv.add(student2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student3.getId()) == null)
                srv.add(student3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student4.getId()) == null)
                srv.add(student4);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkId(){
        Student student1 = new Student("1","nameas",933,"el@.com","profg");
        Student student2 = new Student("2dfgd","name",933,"el@.com","profggg");
        Student student3 = new Student("","ADSA",933,"el@.com","profgsn");
        Student student4 = new Student("2b!","asd",933,"el@.com","1fsd");

        try{
            if (srv.find(student1.getId()) == null)
                srv.add(student1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student2.getId()) == null)
                srv.add(student2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student3.getId()) == null)
                srv.add(student3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(student4.getId()) == null)
                srv.add(student4);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(1,srv.getSize());
    }
}