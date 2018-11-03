package badcompany.ru.roomtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainContract.View {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter mPresenter;
    Button btn_add,btn_get;
    EditText et_name,et_salary;
    ListView lv;
    String nameFromEditText;
    int salaryFromEditText;
    SimpleAdapter adapter;
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Создаём Presenter и в аргументе передаём ему this - эта Activity расширяет интерфейс MainContract.View
        mPresenter = new MainPresenter(this);
        //Загрузка списка при создании экрана

        Log.d(TAG, "OnCreateApp");

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        lv = findViewById(R.id.lv);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_get = findViewById(R.id.btn_get);
        btn_get.setOnClickListener(this);
        et_name = findViewById(R.id.et_name);
        et_salary = findViewById(R.id.et_salary);

        mPresenter.OnCreateApp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                nameFromEditText = et_name.getText().toString();
                salaryFromEditText = Integer.parseInt(et_salary.getText().toString());
                int someId = 1;//заглушка

                Employee employee = new  Employee(someId,nameFromEditText,salaryFromEditText);
                mPresenter.onButtonAddWasCalled(employee);
                break;
            case R.id.btn_get:
                mPresenter.OnButtonGetWasCalled();
                break;
        }
    }

    @Override
    public void showResult(Object object) {
        //myTv.setText(message);
        Log.d(TAG, "showResult()");
        this.adapter = (SimpleAdapter) object;
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }

    @Override
    public void showResultAdd() {
        //this.list = message;
        Log.d(TAG, "showResultAdd()");
        Toast toast = Toast.makeText(getApplicationContext(),
                "Запись добавлена", Toast.LENGTH_SHORT);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        toast.show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void createAdapter(List message) {
        //this.list = message;
        String[] from = { "Id", "Name", "Salary" };
        int[] to = { R.id.tv_item_id, R.id.tv_item_name, R.id.tv_item_salary };

        adapter = new SimpleAdapter(MainActivity.this, message, R.layout.list_item_row,
                from, to);
        lv.setAdapter(adapter);
    }

    //Вызываем у Presenter метод onDestroy, чтобы избежать утечек контекста и прочих неприятностей.
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        Log.d(TAG, "onDestroy()");
    }



}