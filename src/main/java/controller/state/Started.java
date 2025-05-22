package controller.state;

import controller.Controller;
import model.Car;
import model.RoadMutex;

/**
 *
 * @author Usuario
 */
public class Started extends SimulationState {

    public Started(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        if (!controller.isStopped()) {
            roadStart();
        }
    }

    @Override
    public void nextState() {
        controller.setStopped(true);
        controller.setState(new Closing(controller));
        controller.notifyControllButton();
    }

    private void roadStart() {
        int i = 0;
        while (!controller.isStopped()) {
            if (controller.getCarList().size() < controller.getQtdCar()) {
                Car newCar = new Car(controller);
                RoadMutex entrance = controller.getMatrixRoad().getEntrances().get(i);
                Integer[][] positions = { { null, null },
                        { entrance.getRowIndex(), entrance.getColIndex() } };

                if (newCar.enterRoad(entrance)) {
                    controller.getCarList().add(newCar);
                    controller.notifyThreadCounter();
                    controller.notifyMovement(positions);
                    newCar.start();
                } else {
                    System.out.println("Não deu, vamos para a entrada " + (i + 1));
                }

                i++;

                if (i == controller.getMatrixRoad().getEntrances().size()) {
                    i = 0;
                }
                controller.await();
            }
            if (controller.getCarList().size() == controller.getQtdCar()) {
                controller.await();
            }
        }
    }

    @Override
    public String getNextAction() {
        return "PARAR";
    }

}