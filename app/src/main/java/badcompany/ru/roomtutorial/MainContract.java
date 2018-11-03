package badcompany.ru.roomtutorial;

import java.util.List;

public interface MainContract {

    interface View {
        void showResult(Object object);
        void showResultAdd();
        void showProgressBar();
        void hideProgressBar();
        void createAdapter(List list);
    }

    interface Presenter {
        void OnCreateApp();
        void OnButtonGetWasCalled();
        void onButtonAddWasCalled(Employee employee);
        void onDestroy();
    }

    interface Repository {
        List getDataFromModel();
        void AddData(String name, int salary);
    }
}
