package model;

import java.util.concurrent.Semaphore;

/*
 * Representa um bloco da malha viária que possui controle de acesso exclusivo
 */
public class RoadMutex extends Block {

    private final Semaphore semaphore = new Semaphore(1);
    private Car car;

    public RoadMutex(int direction, int xPos, int yPos) {
        super(direction, xPos, yPos);
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}