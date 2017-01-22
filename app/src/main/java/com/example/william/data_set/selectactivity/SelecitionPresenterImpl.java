package com.example.william.data_set.selectactivity;

import com.example.william.data_set.lib.EventBus;
import com.example.william.data_set.lib.GreenRobotEventBus;
import com.example.william.data_set.selectactivity.event.LoadActivityEvent;
import com.example.william.data_set.selectactivity.ui.ListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by william on 24/09/16.
 */
public class SelecitionPresenterImpl implements SelectionPresenter {

    ListView listView;
    EventBus eventBus;
    selectionInteractor selectionInteractor;

    public SelecitionPresenterImpl(ListView listView) {
        this.listView = listView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.selectionInteractor = new SelectionInteractorImpl();
    }

    @Override
    public void onCreate(String mail) {
        eventBus.register(this);
        selectionInteractor.loadActivities(mail);

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void selectActivity(String activityName) {
        selectionInteractor.doSelection(activityName);
    }

    @Override
    public void loadActivities() {

    }

    @Override
    public void showSpecificPositionDialog() {

    }
    @Subscribe
    @Override
    public void onEventMainThread(LoadActivityEvent event) {
        switch (event.getEventType()){
            case LoadActivityEvent.userLoad:
                listView.setuser(event.getUser());
                break;
            case LoadActivityEvent.activityLoad:
                listView.setActivities(event.getPosture(),event.getActivities(),event.getPictures());
                break;

        }

    }
}
