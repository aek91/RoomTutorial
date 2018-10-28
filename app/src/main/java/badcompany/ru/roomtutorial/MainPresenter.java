package badcompany.ru.roomtutorial;

import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    //Компоненты MVP приложения
    private MainContract.View mView;
    private MainContract.Repository mRepository;
    //Сообщение
    String[] from = { "Id", "Name", "Salary" };
    int[] to = { R.id.tv_item_id, R.id.tv_item_name, R.id.tv_item_salary };


    //Обратите внимание на аргументы конструктора - мы передаем экземпляр View
    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
        Log.d(TAG, "Constructor");
    }


    @Override
    public void OnButtonGetWasCalled() {
        SimpleAdapter adapter;

        List message = mRepository.getDataFromModel(); //отправить запрос в Model(Repository) и получить ответ
        adapter = new SimpleAdapter((Context) mView, message, R.layout.list_item_row,
                from, to);
        adapter.notifyDataSetChanged();
        mView.showResult(adapter); //отправить результат во View
        Log.d(TAG, "OnButtonGetWasCalled()");
    }

    @Override
    public void onButtonAddWasCalled(Employee employee) {
        String name = employee.getName();
        int  salary = employee.getSalary();

        mRepository.AddData(name,salary);
        mView.showResultAdd();
        Log.d(TAG, "OnButtonAddWasCalled()");
    }



    @Override
    public void onDestroy() {
        /**
         * Если бы мы работали например с RxJava, в этом классе стоило бы отписываться от подписок
         * Кроме того, при работе с другими методами асинхронного андроида здесь мы боремся с утечкой контекста
         */
        Log.d(TAG, "onDestroy()");
    }




}
