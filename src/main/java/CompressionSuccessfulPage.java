import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CompressionSuccessfulPage extends JFrame
{
    private JPanel SuccessfulPanel;
    private JLabel backLabel;
    private JLabel visitLabel;


    public CompressionSuccessfulPage() throws HeadlessException
    {
        add(SuccessfulPanel);
        setSize(400,400);
        setVisible(true);

        visitLabel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);
                setVisible(false);
                new HomePage();
            }
        });

    }
}
