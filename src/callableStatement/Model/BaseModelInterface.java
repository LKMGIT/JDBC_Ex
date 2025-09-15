package callableStatement.Model;

import java.util.List;

public interface BaseModelInterface<T,ID>{

    public int insert(T entity);
    public int update(T entity);
    public int delete(T entity);
    public List<T> selectAll();
    public T selectById(ID id);

}
