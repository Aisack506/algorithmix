package view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MenuCard extends JPanel {
    // Animation progress from 0.0 (normal) to 1.0 (hover)
    private float animProgress = 0.0f;
    private Timer hoverTimer;
    private boolean isMouseInside = false;

    public MenuCard(String title, String imagePath, ActionListener onClick) {
        setLayout(new BorderLayout(0, 16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        // --- ICON / ROUNDED CONTAINER ---
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int radius = 26;

                // Interpolate background color based on animProgress
                int rBg = (int) (45 + (62 - 45) * animProgress);
                int gBg = (int) (48 + (68 - 48) * animProgress);
                int bBg = (int) (60 + (88 - 60) * animProgress);
                g2d.setColor(new Color(rBg, gBg, bBg));
                g2d.fillRoundRect(0, 0, width, height, radius, radius);

                // Glow effect when hover is active
                if (animProgress > 0.0f) {
                    g2d.setColor(new Color(130, 160, 220, (int) (80 * animProgress)));
                    g2d.setStroke(new BasicStroke(2.5f));
                    g2d.drawRoundRect(1, 1, width - 3, height - 3, radius, radius);
                }

                // Dynamic main border
                int rBorder = (int) (90 + (140 - 90) * animProgress);
                int gBorder = (int) (95 + (150 - 95) * animProgress);
                int bBorder = (int) (115 + (185 - 115) * animProgress);
                g2d.setColor(new Color(rBorder, gBorder, bBorder));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);

                g2d.dispose();
                super.paintComponent(g);
            }
        };

        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(100, 100));
        iconLabel.setOpaque(false);

        // Load and scale the image if it exists
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                URL imgUrl = getClass().getResource(imagePath);
                if (imgUrl != null) {
                    BufferedImage bufferedImage = ImageIO.read(imgUrl);
                    if (bufferedImage != null) {
                        Image scaledImage = bufferedImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                        iconLabel.setIcon(new ImageIcon(scaledImage));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JPanel iconWrapper = new JPanel(new GridBagLayout());
        iconWrapper.setOpaque(false);
        iconWrapper.add(iconLabel);

        // --- CARD TITLE ---
        JLabel nameLabel = new JLabel(title, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(new Color(230, 235, 245));

        add(iconWrapper, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.SOUTH);

        // --- CONFIGURE TIMER FOR SMOOTH TRANSITION ---
        hoverTimer = new Timer(15, e -> {
            boolean needsRepaint = false;

            if (isMouseInside && animProgress < 1.0f) {
                animProgress += 0.15f; // Fade-in speed
                if (animProgress > 1.0f) animProgress = 1.0f;
                needsRepaint = true;
            } else if (!isMouseInside && animProgress > 0.0f) {
                animProgress -= 0.15f; // Fade-out speed
                if (animProgress < 0.0f) animProgress = 0.0f;
                needsRepaint = true;
            }

            if (needsRepaint) {
                iconLabel.repaint();
            } else {
                hoverTimer.stop();
            }
        });

        // --- MOUSE EVENTS ---
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                isMouseInside = true;
                nameLabel.setForeground(Color.WHITE); // Text lights up in white
                hoverTimer.restart();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                isMouseInside = false;
                nameLabel.setForeground(new Color(230, 235, 245)); // Reverts to original color
                hoverTimer.restart();
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
                onClick.actionPerformed(null);
            }
        });
    }
}