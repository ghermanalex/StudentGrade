package StudentGradeMV.Validator;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Domain.TemaLab;
import StudentGradeMV.Validator.IValidator;

public class TemaLabValidator implements IValidator<TemaLab> {

    public String validate(TemaLab t){
        String m = new String();
        m = "";
        if(t.getId()== null || t.getId().equals("")){
            m += "Nr tema invalid\n";
        }else if(t.getDescriere()== null || t.getDescriere().equals("")) {
            m += "Descriere tema invalida\n";
        }
        return m;
    }
}
