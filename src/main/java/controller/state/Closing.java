package controller.state;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Car;

/**
 *
 * @author Usuario
 */
public class Closing extends SimulationState {

    public Closing(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.await();
        if (controller.getCarList().isEmpty()) {
            nextState();
        }
    }

    @Override
    public void nextState() {
        ThreadKiller t = new ThreadKiller(controller.getCarList());
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Closing.class.getName()).log(Level.SEVERE, null, ex);
        }

        controller.getCarList().clear();
        controller.getMatrixRoad().generateMapFromFile(controller.getFilename());
        controller.setState(new Finished(controller));
        controller.notifyThreadCounter();
        controller.notifyReset();
        controller.notifyControllButton();
    }

    @Override
    public String getNextAction() {
        return "FINALIZAR";
    }
}

class ThreadKiller extends Thread {

    List<Car> cars;

    public ThreadKiller(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public void run() {
        for (Car car : cars) {
            car.setOut(true);
            System.out.println(car.isOut());
        }
        for (Car car : cars) {
            try {
                car.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadKiller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}