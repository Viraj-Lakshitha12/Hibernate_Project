package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface CrudDAO<T ,ID extends Serializable>{
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean deleteByPk(ID pk) throws SQLException, ClassNotFoundException;
    T findByPk(ID pk) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

}
