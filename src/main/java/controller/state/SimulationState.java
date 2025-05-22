package controller.state;

import controller.Controller;

public abstract class SimulationState {

    Controller controller;

    public SimulationState(Controller controller) {
        this.controller = controller;
    }

    public abstract void execute();

    public abstract void nextState();

    public abstract String getNextAction();

}