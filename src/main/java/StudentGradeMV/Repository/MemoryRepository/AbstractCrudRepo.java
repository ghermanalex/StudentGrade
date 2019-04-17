package StudentGradeMV.Repository.MemoryRepository;

import StudentGradeMV.Domain.HasId;
import StudentGradeMV.Repository.Repo;
import StudentGradeMV.Validator.IValidator;
import StudentGradeMV.Exceptions.ValidatorException;
import java.util.*;
public abstract class AbstractCrudRepo<ID,E extends HasId<ID>> implements Repo<ID,E> {
    Map<ID,E> repo;
    IValidator<E> validator;
    public AbstractCrudRepo(IValidator v){
        repo=new HashMap<ID,E>();
        validator=v;
    }
    @Override
    public E findOne(ID id){
        if (repo.get(id)==null){
            return null;
        }else{
            if(id==null){
                throw new IllegalArgumentException();
            }else{
                return repo.get(id);
            }
        }

    }

    @Override
    public Iterable<E> findAll(){
        return repo.values();
    }
    @Override
    public E save(E el) throws ValidatorException {
        String msg=validator.validate(el);
        if(msg.equals("")){
            repo.putIfAbsent(el.getId(),el);
            return findOne(el.getId());
        }
        else
            throw new ValidatorException(msg);
    }
    @Override
    public E delete(ID id){
        return repo.remove(id);
    }
    @Override
    public E update(E entity) {
        try {
            if (entity == null) {
                throw new IllegalArgumentException("Entity can not be null!\n");
            } else {
                validator.validate(entity);
                return repo.replace(entity.getId(), entity);
            }
        }catch(ValidatorException e){
            return null;
        }
    }
    //@Override
    public long size(){
        return repo.size();
    }

}