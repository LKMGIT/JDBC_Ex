package callableStatement.Controller;

import java.util.List;

public interface BaseControllerInterface <T, ID>{
    public int insert(T entity);
    public int update(T entity);
    public int delete(T entity);
    public List<T> findAll();
    public T findById(ID id);
}
