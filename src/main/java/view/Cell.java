package view;

import controller.Observer;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class Cell extends JPanelImage {

    private JLabel car;

    public Cell(String path, int width, int height) {
        super(path, width, height);
        setLayout(new GridBagLayout());
    }

    public JLabel getCar() {
        return car;
    }

    public void setCar(JLabel car) {
        this.car = car;
    }

    public void addCarLabel() {
        if (car == null) {
            GridBagConstraints tileConstraint = new GridBagConstraints();
            tileConstraint.fill = GridBagConstraints.BOTH;
            int iconSize = (int) (this.getWidth() * 0.5);
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/assets/car.png")).getImage()
                    .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            JLabel car = new JLabel(icon);

            add(car, tileConstraint);
            setCar(car);
            repaint();
            revalidate();

        }
    }

    public void reset() {
        if (car != null) {
            remove(car);
        }

        setCar(null);
        repaint();
        revalidate();

    }
}