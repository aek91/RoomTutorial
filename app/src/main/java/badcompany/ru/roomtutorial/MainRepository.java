package badcompany.ru.roomtutorial;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainRepository implements MainContract.Repository {

    private final String TAG = "MainRepository";

    //подключение к бд
    private AppDatabase db = App.getInstance().getDatabase();
    private EmployeeDao employeeDao = db.employeeDao();

    private List<HashMap<String, String>> employeesList = new ArrayList<>();

    @Override
    public List getDataFromModel() {
        /** Здесь обращаемся к БД

         */
        GetAllListTask mGetAllListTask = new GetAllListTask();
        mGetAllListTask.execute();
        Log.d(TAG, "getDataFromModel()");
        return employeesList;
    }

    @Override
    public void AddData(String name, String salary) {
        AddTask mAddTask = new AddTask(name,salary);
        mAddTask.execute();
        Log.d(TAG, "AddData()");
    }


    class GetAllListTask  extends AsyncTask<Void, Void, Void> {
        List<Employee> employees;
        int size;
        HashMap<String, String> map;
        Employee employee;

        @Override
        protected Void doInBackground(Void... voids) {
            employees = employeeDao.getAll();
            size = employees.size();

            for (int i = 0; i<size; i++) {
                employee = employeeDao.getById(i);
                long id = employee.getId();
                String name = employee.getName();
                String salary = employee.getSalary();

                map = new HashMap<>();
                map.put("Id", String.valueOf(id));
                map.put("Name", name);
                map.put("Salary", String.valueOf(salary));
                employeesList.add(map);
            }
            return null;
        }

        }

    class AddTask extends AsyncTask<Void, Void, Void> {
        String name;
        String salary;
        List<Employee> employees;

        private AddTask(String name, String salary) {
            this.name = name;
            this.salary = salary;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            employees = employeeDao.getAll(); //узнать длину массива для id
            long id = employees.size();
            Employee employee = new Employee(id, name, salary);
            employeeDao.insert(employee);

            return null;
        }

    }
    }



