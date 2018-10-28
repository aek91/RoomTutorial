package badcompany.ru.roomtutorial;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainRepository implements MainContract.Repository {

    private final String TAG = "MainRepository";

    //подключение к бд
    AppDatabase db = App.getInstance().getDatabase();
    EmployeeDao employeeDao = db.employeeDao();

    private List<HashMap<String, String>> employeesList = new ArrayList<>();

    @Override
    public List getDataFromModel() {
        Log.d(TAG, "getDataFromModel()");
        /** Здесь обращаемся к БД
         * Я специально ничего не пишу, чтобы не загромождать пример
         * DBHelper'ами и прочими не относяшимеся к теме объектами.
         * Поэтому я буду возвращать строку Сосисочная =)
         */
        GetAllListTask mGetAllListTask = new GetAllListTask();
        mGetAllListTask.execute();
        return employeesList;
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
                int salary = employee.getSalary();

                map = new HashMap<String, String>();
                map.put("Id", String.valueOf(id));
                map.put("Name", name);
                map.put("Salary", String.valueOf(salary));
                //Log.i(TAG, String.valueOf(map));
                employeesList.add(map);
            }
            return null;
        }

        }
    }

