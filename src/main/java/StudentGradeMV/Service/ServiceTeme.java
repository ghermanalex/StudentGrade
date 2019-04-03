package StudentGradeMV.Service;

import StudentGradeMV.Domain.TemaLab;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.TemaLabRepo;

public class ServiceTeme {
    private TemaLabRepo rep;
    public ServiceTeme(TemaLabRepo rep){this.rep=rep;}

    /**
     * Adauga tema
     * @param s
     * @return tema adaugata
     */
    public TemaLab add(TemaLab s) throws ValidatorException {
        return rep.save(s);
    }

    /**
     * sterge tema
     * @param id
     * @return tema stearsa
     */
    public TemaLab del(Integer id){
        return rep.delete(id);
    }

    /**
     * Modifica tema
     * @param s
     * @return tema modificata
     */
    public TemaLab mod(TemaLab s){
        return rep.update(s);
    }

    /**
     * Cauta tema dupa id
     * @param id
     * @return tema gasita
     */
    public TemaLab find(Integer id){
        return rep.findOne(id);
    }

    /**
     * @return temele
     */
    public Iterable<TemaLab> all(){
        return rep.findAll();
    }

    public int getSize() {return (int) rep.size();}
}
