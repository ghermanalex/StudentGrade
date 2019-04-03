package StudentGradeMV.Repository.MemoryRepository;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Validator.IValidator;
import StudentGradeMV.Domain.TemaLab;
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

public class TemaLabRepo extends AbstractCrudRepo<Integer,TemaLab> {

    private String fName;
    private DocumentBuilderFactory builderFactory;
    private Boolean check;

    public TemaLabRepo(IValidator<TemaLab> val, String n, Boolean i){
        super(val);
        this.fName=n;
        builderFactory= DocumentBuilderFactory.newInstance();
        this.check = true;
        if (check)
            loadFromFile();
    }
    public void loadFromFile(){
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document d=db.parse(new File(fName));
            Element e = d.getDocumentElement();
            NodeList l=e.getElementsByTagName("tema");
            for(int i=0;i<l.getLength();i++){
                Element a=(Element) l.item(i);
                String id=a.getAttribute("id");
                int nr=Integer.parseInt(id);
                NodeList l1=a.getElementsByTagName("descriere");
                String nume=l1.item(0).getTextContent();
                NodeList l2=a.getElementsByTagName("saptamanaLimita");
                String gr=l2.item(0).getTextContent();
                int dd=Integer.parseInt(gr);
                NodeList l3=a.getElementsByTagName("saptamanaPredarii");
                String mail=l3.item(0).getTextContent();
                int sp=Integer.parseInt(mail);
                TemaLab t=new TemaLab(nr,nume,sp,dd);
                super.save(t);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
    private void writeToFile(){
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document doc=db.newDocument();
            Element r = doc.createElement("tema");
            for(TemaLab s:findAll()) {
                Element e = doc.createElement("tema");
                e.setAttribute("id",s.getId().toString());
                Element numee=doc.createElement("descriere");
                Text num=doc.createTextNode(s.getDescriere());
                numee.appendChild(num);
                e.appendChild(numee);
                Element grupa=doc.createElement("saptamanaLimita");
                int g=s.getTermenLimita();
                String grr=Integer.toString(g);
                Text gr=doc.createTextNode(grr);
                grupa.appendChild(gr);
                e.appendChild(grupa);
                Element mail=doc.createElement("saptamanaPredarii");
                Text email=doc.createTextNode(s.getSaptammanaPredarii() + "");
                mail.appendChild(email);
                e.appendChild(mail);
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
    public TemaLab save(TemaLab el) throws ValidatorException {
        TemaLab t= super.save(el);
        if (check)
            writeToFile();
        return t;
    }
    @Override
    public TemaLab delete(Integer id){
        TemaLab t=super.delete(id);
        if (check)
            writeToFile();
        return t;
    }
    @Override
    public TemaLab update(TemaLab tt){
        TemaLab t=super.update(tt);
        if (check)
            writeToFile();
        return t;
    }


}