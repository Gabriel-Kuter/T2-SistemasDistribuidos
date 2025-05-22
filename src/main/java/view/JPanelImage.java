package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
class JPanelImage extends JPanel {

    private Image background;
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JPanelImage(String path, int width, int height) {
        this.width = width;
        this.height = height;
        setOpaque(false);

        // 🐞 DEBUG para verificar se o recurso foi encontrado
        java.net.URL location = getClass().getResource(path);
        System.out.println("DEBUG: carregando imagem '" + path + "' → " + location);

        if (location == null) {
            throw new RuntimeException("Imagem não encontrada no classpath: " + path);
        }

        background = new ImageIcon(location).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}