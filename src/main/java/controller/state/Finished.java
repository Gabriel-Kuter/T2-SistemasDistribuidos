package controller.state;

import controller.Controller;

/**
 *
 * @author Usuario
 */
public class Finished extends SimulationState {

    public Finished(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.await();
    }

    @Override
    public void nextState() {
        controller.setStopped(false);
        controller.setState(new Started(controller));
        controller.notifyControllButton();
    }

    @Override
    public String getNextAction() {
        return "INICIAR";
    }

}