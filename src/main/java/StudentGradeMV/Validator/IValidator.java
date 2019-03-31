package StudentGradeMV.Validator;
import StudentGradeMV.Exceptions.ValidatorException;
public interface IValidator<E> {
    void validate(E entity) throws ValidatorException;
}