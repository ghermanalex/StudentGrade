package StudentGradeMV.Repository.MemoryRepository;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Validator.IValidator;
import StudentGradeMV.Domain.Student;
import StudentGradeMV.Validator.StudentValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class StudentRepo extends AbstractCrudRepo<String, Student> {
    private String fName;
    private DocumentBuilderFactory builderFactory;
    private Boolean check;

    public StudentRepo(IValidator<Student> val, String n, Boolean i){
        super(val);
        this.fName=n;
        builderFactory=DocumentBuilderFactory.newInstance();
        check = true;
        if (check)
            loadFromFile();
    }

    public void loadFromFile(){
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document d=db.parse(new File(fName));
            Element e = d.getDocumentElement();
            NodeList l=e.getElementsByTagName("student");
            for(int i=0;i<l.getLength();i++){
                Element a=(Element) l.item(i);
                String id=a.getAttribute("id");
                NodeList l1=a.getElementsByTagName("nume");
                String nume=l1.item(0).getTextContent();
                NodeList l2=a.getElementsByTagName("grupa");
                String gr=l2.item(0).getTextContent();
                int grupa=Integer.parseInt(gr);
                NodeList l3=a.getElementsByTagName("email");
                String mail=l3.item(0).getTextContent();
                NodeList l4=a.getElementsByTagName("profesor");
                String profesor=l4.item(0).getTextContent();
                Student s=new Student(id,nume,grupa,mail,profesor);
                super.save(s);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
    private void writeToFile(){
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document doc=db.newDocument();
            Element r = doc.createElement("inbox");
            for(Student s:findAll()) {

                Element e = doc.createElement("student");
                e.setAttribute("id",s.getId());
                Element numee=doc.createElement("nume");
                Text num=doc.createTextNode(s.getNume());
                numee.appendChild(num);
                e.appendChild(numee);
                Element grupa=doc.createElement("grupa");
                int g=s.getGrupa();
                String grr=Integer.toString(g);
                Text gr=doc.createTextNode(grr);
                grupa.appendChild(gr);
                e.appendChild(grupa);
                Element mail=doc.createElement("email");
                Text email=doc.createTextNode(s.getEmail());
                mail.appendChild(email);
                e.appendChild(mail);
                Element profesor=doc.createElement("profesor");
                Text prof=doc.createTextNode(s.getIndrumator());
                profesor.appendChild(prof);
                e.appendChild(profesor);
                r.appendChild(e);
            }
            doc.appendChild(r);
            TransformerFactory tf=TransformerFactory.newInstance();
            Transformer t=tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(fName)));
        }
        catch (Exception e){e.printStackTrace();}
    }
    @Override
    public Student save(Student el) throws ValidatorException {
        Student t= super.save(el);
        if (check)
            writeToFile();
        return t;
    }
    @Override
    public Student delete(String id){
        Student t=super.delete(id);
        if (check)
            writeToFile();
        return t;
    }
    @Override
    public Student update(Student tt){
        Student t=super.update(tt);
        if (check)
            writeToFile();
        return t;
    }
}