package StudentGradeMV;

import StudentGradeMV.Exceptions.*;
import StudentGradeMV.Repository.TxtFileRepository.StudentFileRepo;
import StudentGradeMV.Repository.XMLFileRepository.NotaXMLRepo;
import StudentGradeMV.Repository.XMLFileRepository.StudentXMLRepo;
import StudentGradeMV.Repository.XMLFileRepository.TemaLabXMLRepo;
import StudentGradeMV.Service.TxtFileService.StudentService;
import StudentGradeMV.Service.XMLFileService.NotaXMLService;
import StudentGradeMV.Service.XMLFileService.StudentXMLService;
import StudentGradeMV.Service.XMLFileService.TemaLabXMLService;
import StudentGradeMV.Validator.*;
import StudentGradeMV.UI.*;

import javax.xml.validation.Validator;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ValidatorException {
        //System.out.println("Hello World!");
        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
        NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"./data/StudentiXML.xml");
        //StudentFileRepo strepo=new StudentF   ileRepo("./data/studenti.txt",vs);
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"./data/TemaLaboratorXML.xml");
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"./data/NotaXML.xml");

        //StudentService sttt = new StudentService(strepo);;

        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);
        ui ui=new ui(stsrv,tmsrv,ntsrv);
        ui.run();
    }
}