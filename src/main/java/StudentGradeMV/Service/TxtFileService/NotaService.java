package StudentGradeMV.Service.TxtFileService;


import StudentGradeMV.Domain.Nota;
import StudentGradeMV.Repository.TxtFileRepository.NotaFileRepo;

public class NotaService extends AbstractService<Integer, Nota> {
    public NotaService(NotaFileRepo notaRepo){
        super(notaRepo);
    }
}
