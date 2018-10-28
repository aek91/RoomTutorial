package badcompany.ru.roomtutorial;

import java.util.List;

public interface MainContract {

    interface View {
        void showResult(Object object);
        void showResultAdd();
    }

    interface Presenter {
        void OnButtonGetWasCalled();
        void onDestroy();
        void onButtonAddWasCalled(Employee employee);
    }

    interface Repository {
        List getDataFromModel();
        void AddData(String name, int salary);
    }
}
