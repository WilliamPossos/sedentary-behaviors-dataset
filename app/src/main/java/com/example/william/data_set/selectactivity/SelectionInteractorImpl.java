package com.example.william.data_set.selectactivity;


/**
 * Created by william on 24/09/16.
 */
public class SelectionInteractorImpl implements selectionInteractor {

    selectionRepository selectionRepository;

    public SelectionInteractorImpl() {
        this.selectionRepository = new SelectionRepositoryImpl();
    }

    @Override
    public void doSelection(String activityName) {
        selectionRepository.updateActivityList();

    }

    @Override
    public void loadActivities(String mail) {
        selectionRepository.loadActivities(mail);
    }
}
