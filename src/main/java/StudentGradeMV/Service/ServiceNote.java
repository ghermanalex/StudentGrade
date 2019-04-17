package StudentGradeMV.Service;

import StudentGradeMV.Domain.Nota;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.NotaRepo;

import java.util.Map;

public class ServiceNote {
    private NotaRepo rep;

    public ServiceNote(NotaRepo rep)
    {
        this.rep=rep;
    }
    /**
     * Adauga Nota
     * Returneaza Nota adaugata
     */
    public Nota add(Nota s) throws ValidatorException {
        return rep.save(s);
    }

    public Iterable<Nota> all(){
        return rep.findAll();
    }
    public Nota find(Map.Entry<String,Integer> nota) {return rep.findOne(nota.getValue());}
    public long getSize() {return  rep.size();}
}
