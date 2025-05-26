package view;

import controller.Controller;
import controller.Observer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Index extends JFrame implements Observer {

    Table road;
    Controller controller;
    JPanelImage settingsPanel;

    GridBagConstraints gbc = new GridBagConstraints();

    ArrayList<JComponent> menuComponents = new ArrayList<>();
    JButton btnInicio; // Controle que inicia ou finaliza a utilização de Threads
    JLabel lbQtCarro; // Label de indicação: quantidade de carros
    JLabel lblInsertionSpeed;// Label de indicação: delay para inserção de novas threads
    JLabel lbQtCarrosAtivos; // Label de indicação: contador de threads
    JLabel lblCounter; // Label de indicação: contador de threads notificável

    JComboBox<String> cbMalhas;
    JSpinner tfInsertionSpeed;
    JSpinner tfCar;

    public Index() {
        controller = Controller.getInstance();
        controller.addObserver(this);

        setTitle("Malha Viária");
        setSize(1400, 825);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.black);
        buildPanels();
        addActions();

        controller.notifyControllButton();
    }

    public void buildPanels() {
        //Menu
        gbc.gridx = 0;
        gbc.gridy = 0;
        settingsPanel = buildMenu(); 
        add(settingsPanel, gbc);

        //Grid Malha Rodoviária
        gbc.gridy = 1;
        road = new Table();
        add(road, gbc);

        controller.notifyInitFiles();
    }

    private JPanelImage buildMenu() {
        settingsPanel = new JPanelImage("/assets/top.png", 1300, 65);
        settingsPanel.setPreferredSize(new Dimension(settingsPanel.getWidth(), settingsPanel.getHeight()));
        settingsPanel.setBorder(new LineBorder(new Color(255,51,255)));
        settingsPanel.setLayout(new GridBagLayout());

        btnInicio = new JButton("Iniciar");
        cbMalhas = new JComboBox();
        lbQtCarro = new JLabel("Quantidade de carros: ");
        lbQtCarro.setForeground(Color.white);
        lblInsertionSpeed = new JLabel("Delay de inserção: ");
        lblInsertionSpeed.setForeground(Color.white);
        lbQtCarrosAtivos = new JLabel("Quantidade de Threads Ativas: ");
        lbQtCarrosAtivos.setForeground(Color.white);
        lblCounter = new JLabel("0");
        lblCounter.setForeground(Color.white);
        tfCar = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
        tfInsertionSpeed = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        menuComponents.add(btnInicio);
        menuComponents.add(cbMalhas);
        menuComponents.add(lbQtCarro);
        menuComponents.add(tfCar);
        menuComponents.add(lblInsertionSpeed);
        menuComponents.add(tfInsertionSpeed);
        menuComponents.add(lbQtCarrosAtivos);
        menuComponents.add(lblCounter);

        gbc.gridx = 0;
        gbc.gridy = 0;

        for (int i = 0; i < menuComponents.size(); i++) {
            gbc.gridx = i;
            gbc.weightx = 1;
            settingsPanel.add(menuComponents.get(i), gbc);
        }

        return settingsPanel;
    }

    // Controller Actions
    private void addActions() {
        btnInicio.addActionListener((ActionEvent e) -> {
            if (controller.isStopped()) {
                controller.setQtdCar((int) tfCar.getValue());
                controller.setAwait((int) tfInsertionSpeed.getValue());
            }
            controller.getControllerState().nextState();
        });

        cbMalhas.addActionListener((ActionEvent e) -> {
            controller.setFilename(controller.getFilePaths().get((String) cbMalhas.getSelectedItem()));
            controller.getMatrixRoad().generateMapFromFile(controller.getFilename());
            road.draw();
        });
    }

    // Observer updates
    @Override
    public void updateControllStatus(boolean isStopped) {
        cbMalhas.setEnabled(isStopped);
        btnInicio.setText(controller.getControllerState().getNextAction());
    }

    @Override
    public void updateThreadCounter(int counter) {
        lblCounter.setText(counter + "");
    }

    @Override
    public void updateCarPosition(Integer[][] blockPositions) {
    }

    @Override
    public void initRoadFiles(String[] roadFiles) {
        for (String file : roadFiles) {
            cbMalhas.addItem(file);
        }
    }

    @Override
    public void reset() {
    }

}