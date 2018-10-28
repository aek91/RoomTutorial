package badcompany.ru.roomtutorial;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("Select * From employee")
    List<Employee> getAll();

    @Query("Select * From employee WHERE id = :id")
    Employee getById(long id);

    @Query("SELECT * FROM employee ORDER BY salary DESC")
    List<Employee> getAllOrderBySalary();

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

}
