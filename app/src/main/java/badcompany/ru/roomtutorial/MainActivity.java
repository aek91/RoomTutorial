package badcompany.ru.roomtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainContract.View {

    private static final String TAG = "MainActivity";

    private MainContract.Presenter mPresenter;

    Button btn_add,btn_get,btn_test;
    EditText et_name,et_salary;
    TextView tv;
    ListView lv;
    /*
    AddTask at;
    GetTask gt;
    GetAllTask gat;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Создаём Presenter и в аргументе передаём ему this - эта Activity расширяет интерфейс MainContract.View
        mPresenter = new MainPresenter(this);

        lv = (ListView) findViewById(R.id.lv);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_test = (Button) findViewById(R.id.btn_get1);
        //et_id = (EditText) findViewById(R.id.et_id);
        et_name = (EditText) findViewById(R.id.et_name);
        et_salary = (EditText) findViewById(R.id.et_salary);
        tv = (TextView) findViewById(R.id.tv);

        btn_add.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        btn_test.setOnClickListener(this);

       // gat = new GetAllTask();
        //gat.execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                /*
                at = new AddTask();
                at.execute();
                */
                break;
            case R.id.btn_get:
                mPresenter.OnButtonGetWasCalled();
                break;
            case R.id.btn_test:
                //gt = new GetTask();
                //gt.execute();
                break;
        }
    }

    @Override
    public void showResult(Object object) {
        //myTv.setText(message);
        Log.d(TAG, "showMessage()");
        lv.setAdapter((ListAdapter) object);
    }

    //Вызываем у Presenter метод onDestroy, чтобы избежать утечек контекста и прочих неприятностей.
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
        /*
    class AddTask extends AsyncTask<Void, Void, Void> {

        List<Employee> employees;
        @Override
        protected Void doInBackground(Void... voids) {
            employees = employeeDao.getAll();

            long id = employees.size();
            String name = et_name.getText().toString();
            int salary = Integer.parseInt(et_salary.getText().toString());

            Employee employee = new Employee(id, name, salary);
            employeeDao.insert(employee);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Запись добавлена", Toast.LENGTH_SHORT).show();
        }
    }

    class GetAllTask extends AsyncTask<Void, Void, Void> {

        List<Employee> employees;
        int size;
        HashMap<String, String> map;
        Employee employee;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //убить адаптер
            arraylist.clear();
        }
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
                arraylist.add(map);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            String[] from = { "Id", "Name", "Salary" };
            int[] to = { R.id.tv_item_id, R.id.tv_item_name, R.id.tv_item_salary };


            //tv.setText(employeesList.toString());
        }

    }

    class GetTask extends AsyncTask<Void, Void, Void> {

        Employee employee;
        String txtName;
        String txtId;
        String txtSalary;

        @Override
        protected Void doInBackground(Void... voids) {
            employee = employeeDao.getById(1);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            txtName = employee.getName();
            txtId = String.valueOf(employee.getId());
            txtSalary = String.valueOf(employee.getSalary());
            tv.setText(employee.toString());
        }
    }
        */

}