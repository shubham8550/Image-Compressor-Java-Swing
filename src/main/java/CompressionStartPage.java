import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class CompressionStartPage extends JFrame {

    private JProgressBar progressBar1;
    private JLabel centerlable;
    private JPanel mainpanel;
    private JLabel fileLable;

    CompressionStartPage(String location){
        mainpanel.setBorder(new LineBorder( Color.RED,5));
        add(mainpanel);
        setSize(400,400);

        progressBar1.setBorder(new LineBorder( Color.cyan,25));

        fileLable.setText(FilenameUtils.getName(location));

        setVisible(true);

        new StartCompression(this,progressBar1,location).start();

    }
}
