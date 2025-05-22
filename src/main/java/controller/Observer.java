package controller;

public interface Observer {

    public void updateCarPosition(Integer[][] blockPositions);

    public void updateControllStatus(boolean status);

    public void updateThreadCounter(int counter);

    public void initRoadFiles(String[] roadFiles);

    public void reset();

}