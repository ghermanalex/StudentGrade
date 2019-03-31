package StudentGradeMV.Repository.MemoryRepository;
import StudentGradeMV.Domain.Nota;
import StudentGradeMV.Validator.IValidator;


public class NotaRepo extends AbstractCrudRepo<Integer, Nota> {
    public NotaRepo(IValidator<Nota> v){ super(v);
    }
}