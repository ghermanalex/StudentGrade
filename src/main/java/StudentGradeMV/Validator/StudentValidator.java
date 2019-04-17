package StudentGradeMV.Validator;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Domain.Student;

public class StudentValidator implements IValidator<Student> {

    public String validate(Student s) throws ValidatorException {
        String errors="";

        return errors;
    }
}