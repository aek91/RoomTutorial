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

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private static boolean isString(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    //внимание на аргументы конструктора - мы передаем экземпляр View
    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
        Log.d(TAG, "Constructor");
    }

    @Override
    public void OnCreateApp() {
        CreateTask createTask = new CreateTask();
        createTask.execute();
        Log.d(TAG, "OnCreateApp()");
    }


    @Override
    public void onButtonAddWasCalled(Employee employee) {

        String name = employee.getName();
        String  salary = employee.getSalary();
        //проверка внесенных данных
        boolean isInt = isInteger(String.valueOf(salary));
        if (name.equals("")) {
            mView.createNegativeToast("Не найдено Name");
        }
        if (isInt) {
            mRepository.AddData(name,salary);//Отправляем запрос на добавление
            //Обновляем список
            GetTask getTask = new GetTask();
            getTask.execute();
            mView.showResultAdd();
            Log.d(TAG, "OnButtonAddWasCalled()");
        } else {
            mView.createNegativeToast("Не найдено Salary");
        }

    }

    @Override
    public void onDestroy() {
        /**
         * Если бы мы работали например с RxJava, в этом классе стоило бы отписываться от подписок
         * Кроме того, при работе с другими методами асинхронного андроида здесь мы боремся с утечкой контекста
         */
        Log.d(TAG, "onDestroy()");
    }

    class CreateTask extends AsyncTask<Void, Void, Void> {

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
        }
    }

    class GetTask extends CreateTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            message.clear();
        }
    }

}
