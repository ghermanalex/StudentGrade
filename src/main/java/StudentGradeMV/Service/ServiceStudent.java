package StudentGradeMV.Service;

import StudentGradeMV.Domain.Student;
import StudentGradeMV.Exceptions.ValidatorException;
import StudentGradeMV.Repository.MemoryRepository.StudentRepo;

public class ServiceStudent {
    private StudentRepo rep;
    public ServiceStudent(StudentRepo rep){this.rep=rep;}
    /**
     * Adauga student
     * Returneaza studentul adaugat
     */
    public Student add(Student s) throws ValidatorException {
        return rep.save(s);
    }
    /**
     * Sterge student
     * @param id
     * @return studentul sters
     */
    public Student del(String id){
        return rep.delete(id);
    }

    /**
     * Modifica student
     * @param s
     * @return noul student
     */
    public Student mod(Student s){
        return rep.update(s);
    }

    /**
     * Cauta student dupa id
     * @param id
     * @return studentul gasit
     */
    public Student find(String id){
        return rep.findOne(id);
    }

    /**
     * @return studentii
     */
    public Iterable<Student> all(){
        return rep.findAll();
    }

    public int getSize() {return (int) rep.size();}
}