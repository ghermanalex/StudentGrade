package StudentGradeMV.Repository.MemoryRepository;
import StudentGradeMV.Validator.IValidator;
import StudentGradeMV.Domain.Student;

public class StudentRepo extends AbstractCrudRepo<String, Student> {
    public StudentRepo(IValidator<Student> v){ super(v);
    }
}