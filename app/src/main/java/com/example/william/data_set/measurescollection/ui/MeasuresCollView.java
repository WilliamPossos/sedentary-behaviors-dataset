package com.example.william.data_set.measurescollection.ui;

/**
 * Created by william on 8/09/16.
 */
public interface MeasuresCollView {

    void startCapture();
    void restartCapture();
    void finishCapture();

    void showStartButton();
    void hideStartButton();
    void showRestartButton();
    void hideRestartButton();

    void updateProgressBar(int i, int j);

    void showRestartDialog(int title, int message);
}
