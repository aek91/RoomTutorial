package badcompany.ru.roomtutorial;

import java.util.List;

public interface MainContract {

    interface View {
        void showResultAdd();
        void showProgressBar();
        void hideProgressBar();
        void createAdapter(List list);
        void createNegativeToast(String string);
    }

    interface Presenter {
        void OnCreateApp();
        void onButtonAddWasCalled(Employee employee);
        void onDestroy();
    }

    interface Repository {
        List getDataFromModel();
        void AddData(String name, String salary);
    }
}
