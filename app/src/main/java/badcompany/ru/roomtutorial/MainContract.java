package badcompany.ru.roomtutorial;

import java.util.List;

public interface MainContract {

    interface View {
        void showResult(Object object);
    }

    interface Presenter {
        void OnButtonGetWasCalled();
        void OnButtonAddWasCalled();
        void onDestroy();
    }

    interface Repository {
        List getDataFromModel();
    }
}
