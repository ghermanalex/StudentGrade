package StudentGradeMV;

import StudentGradeMV.Domain.TemaLab;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.TemaLabRepo;
import StudentGradeMV.Service.ServiceTeme;
import StudentGradeMV.Validator.TemaLabValidator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AddTheme {

    private String dirPath = System.getProperty("user.dir");
    private TemaLabRepo rep = new TemaLabRepo(new TemaLabValidator(),dirPath + "\\data\\TemaLaboratorXML.xml",false);
    private ServiceTeme srv = new ServiceTeme(rep);

    @Test
    public void addTheme() {
        TemaLab teme= new TemaLab(123,"fgdfgd",12,12);
        TemaLab teme1 = new TemaLab(1,"dfgfdgfdg",1,10);

        try{
            if (srv.find(teme.getId()) == null)
                srv.add(teme);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(8,srv.getSize());

        try{
            if (srv.find(teme1.getId()) == null)
                srv.add(teme1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(8,srv.getSize());
    }

    @Test
    public void checkDuplicates(){
        TemaLab teme= new TemaLab(123,"fgdfgd",12,12);

        try{
            if (srv.find(teme.getId()) == null)
                srv.add(teme);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(teme.getId()) == null)
                srv.add(teme);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(8,srv.getSize());
    }

    @Test
    public void checkId(){
        TemaLab teme= new TemaLab(123,"fgdfgd",12,12);
        TemaLab teme1 = new TemaLab(-1,"dfgfdgfdg",1,10);

        try{
            if (srv.find(teme.getId()) == null)
                srv.add(teme);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(teme1.getId()) == null)
                srv.add(teme1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        assertEquals(8,srv.getSize());
    }

    @Test
    public void checkDeadline(){
        TemaLab teme= new TemaLab(123,"fgdfgd",12,12);
        TemaLab teme1 = new TemaLab(1,"dfgfdgfdg",1,14);
        TemaLab teme2 = new TemaLab(10,"dfgfdgfdg",0,13);
        TemaLab teme4 = new TemaLab(10,"dfgfdgfdg",-1,2);
        TemaLab teme3 = new TemaLab(10,"dfgfdgfdg",11,6);

        try{
            if (srv.find(teme.getId()) == null)
                srv.add(teme);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(teme1.getId()) == null)
                srv.add(teme1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(teme2.getId()) == null)
                srv.add(teme2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        try{
            if (srv.find(teme4.getId()) == null)
                srv.add(teme4);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv.find(teme3.getId()) == null)
                srv.add(teme3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(8,srv.getSize());
    }

    @Test
    public void checkSaptPrimire(){
        String dirPath2 = System.getProperty("user.dir");
        TemaLabRepo rep2 = new TemaLabRepo(new TemaLabValidator(),dirPath2 + "\\data\\TemaLaboratorXML.xml",false);
        ServiceTeme srv2 = new ServiceTeme(rep2);

        TemaLab teme= new TemaLab(123,"fgdfgd",12,12);
        TemaLab teme1 = new TemaLab(1,"dfgfdgfdg",156,1);
        TemaLab teme2 = new TemaLab(10,"dfgfdgfdg",0,7);
        TemaLab teme3 = new TemaLab(10,"dfgfdgfdg",-1,7);

        try{
            if (srv.find(teme.getId()) == null)
                srv2.add(teme);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv2.find(teme1.getId()) == null)
                srv2.add(teme1);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv2.find(teme3.getId()) == null)
                srv2.add(teme3);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }

        try{
            if (srv2.find(teme2.getId()) == null)
                srv2.add(teme2);
        }catch (ValidatorException e) {
            e.printStackTrace();
        }
        assertEquals(8,srv2.getSize());
    }
}
