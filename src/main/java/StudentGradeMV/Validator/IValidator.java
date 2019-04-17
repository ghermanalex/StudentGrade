package StudentGradeMV.Validator;
import StudentGradeMV.Exceptions.ValidatorException;
public interface IValidator<E> {
    String validate(E entity) throws ValidatorException;
}