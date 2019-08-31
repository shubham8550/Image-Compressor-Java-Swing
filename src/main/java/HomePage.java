import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage extends JFrame {
    private JPanel UploadPanel;
    private JLabel selectLabel;
    private JLabel uploadLabel;
    private JLabel imageLabel;
    private JPanel centerPanel;
    private String filePath = "";

    public HomePage() throws HeadlessException {
        add(UploadPanel);
        setVisible(true);
        setTitle("SP Image Compressor");
        setSize(450, 450);

        selectLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int ret = selectFile();
                if(ret == 0)
                {
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new File(filePath));
                    } catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    assert img != null;
                    Image dimg = img.getScaledInstance(centerPanel.getWidth(),centerPanel.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    imageLabel.setIcon(imageIcon);
                }
                else
                {

                }

            }
        });

        uploadLabel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);

                if(filePath.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please Select The Image First");
                }
                else
                {
                    setVisible(false);
                    new CompressionStartPage(filePath);
                }

            }
        });

    }

    private int selectFile() {
        System.out.println("In");
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(imageFilter);
        int returnVal = fc.showOpenDialog(fc);


        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            filePath = fc.getSelectedFile().getAbsolutePath();
            return 0;
        }
        else
        {
            return 1;
        }

    }


}