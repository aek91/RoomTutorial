package badcompany.ru.roomtutorial;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    //Компоненты MVP приложения
    private MainContract.View mView;
    private MainContract.Repository mRepository;
    //Сообщение

    List message;

    //внимание на аргументы конструктора - мы передаем экземпляр View
    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
        Log.d(TAG, "Constructor");
    }

    @Override
    public void OnCreateApp() {
        GetTask getTask = new GetTask();
        getTask.execute();
        Log.d(TAG, "OnCreateApp()");
    }

    @Override
    public void OnButtonGetWasCalled() {
        GetTask getTask = new GetTask();
        getTask.execute();
        Log.d(TAG, "OnButtonGetWasCalled()");
    }

    @Override
    public void onButtonAddWasCalled(Employee employee) {
        //Отправляем запрос на добавление
        String name = employee.getName();
        int  salary = employee.getSalary();
        mRepository.AddData(name,salary);

        //Обновляем список
        GetTask getTask = new GetTask();
        getTask.execute();

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

    class GetTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mView.showProgressBar();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            message = mRepository.getDataFromModel(); //отправить запрос в Model(Repository) и получить ответ
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mView.createAdapter(message);
            mView.hideProgressBar();
            //mView.showResult(adapter); //отправить результат во View
        }
    }

}
