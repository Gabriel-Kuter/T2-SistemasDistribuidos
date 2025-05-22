package controller;

import controller.state.Finished;
import controller.state.SimulationState;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Car;
import model.RoadDirection;
import utils.MatrixUtils;

public class Controller extends Thread {

    private final MatrixUtils roadInstance = MatrixUtils.getInstance();
    private static Controller instance;
    private SimulationState state;
    private List<Observer> roadObservers = new ArrayList<>();
    private Map<String, String> filePaths = new HashMap<String, String>();
    private String filename = "src/main/resources/casefiles/malhaTesteComplexo.txt";

    private Controller() {
        RoadDirection.toMap();
        roadInstance.generateMapFromFile(filename);
        initMapFiles();
        state = new Finished(this);
        this.start();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public void addObserver(Observer obs) {
        this.roadObservers.add(obs);
    }

    public void removeObserver(Observer obs) {
        this.roadObservers.remove(obs);
    }

    public void notifyMovement(Integer[][] blockPositions) {
        for (Observer observer : roadObservers) {
            observer.updateCarPosition(blockPositions);
        }
    }

    public void notifyControllButton() {
        for (Observer observer : roadObservers) {
            observer.updateControllStatus(isStopped());
        }
    }

    public void notifyThreadCounter() {
        for (Observer observer : roadObservers) {
            observer.updateThreadCounter(carList.size());
        }
    }

    public void notifyInitFiles() {
        Set<String> keys = filePaths.keySet();
        for (Observer observer : roadObservers) {
            observer.initRoadFiles(keys.toArray(new String[keys.size()]));
        }
    }

    public void notifyReset() {
        for (Observer observer : roadObservers) {
            observer.reset();
        }
    }

    private void initMapFiles() {
        File folder = new File("src/main/resources/casefiles");
        File[] listOfFiles = folder.listFiles();
        String[] filenames = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filenames[i] = "Malha " + i;
                filePaths.put(filenames[i], listOfFiles[i].getPath());
            }
        }
    }

    public MatrixUtils getMatrixRoad() {
        return roadInstance;
    }

    public Map<String, String> getFilePaths() {
        return filePaths;
    }

    public SimulationState getControllerState() {
        return state;
    }

    public void setState(SimulationState state) {
        this.state = state;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private List<Car> carList = new ArrayList<>();
    private int qtdCar;
    private int await;
    private boolean stopped = true;

    public List<Car> getCarList() {
        return carList;
    }

    public void setQtdCar(int qtdCar) {
        this.qtdCar = qtdCar;
    }

    public int getQtdCar() {
        return qtdCar;
    }

    public void setAwait(int await) {
        this.await = await * 100;
    }

    public int getAwait() {
        return this.await;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void await() {
        try {
            sleep(await);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            state.execute();
        }
    }
}