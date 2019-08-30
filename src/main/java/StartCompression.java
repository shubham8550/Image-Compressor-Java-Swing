import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.swing.*;

public class StartCompression extends Thread {
    JProgressBar progressBar;
    JFrame frame;
    String location;

    StartCompression(JFrame frame,JProgressBar progressBar,String location) {
        this.frame=frame;
        this.progressBar=progressBar;
        this.location=location;
    }

        public void run(){
        try {
            progressBar.setValue(5);

            File input = new File(location);
            BufferedImage image = ImageIO.read(input);

            File compressedImageFile = new File("compressed_image.jpg");
            OutputStream os = new FileOutputStream(compressedImageFile);

            progressBar.setValue(20);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(FilenameUtils.getExtension(location));
            ImageWriter writer = (ImageWriter) writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            progressBar.setValue(40);

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.2f);  // Change the quality value you prefer

            progressBar.setValue(70);

            writer.write(null, new IIOImage(image, null, null), param);

            progressBar.setValue(90);

            os.close();
            ios.close();
            writer.dispose();

            progressBar.setValue(100);
            frame.setVisible(false);
            new CompressionSuccessfullPage();
            frame.dispose();

        }catch (Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(frame,Res.ImageCompressErrorMessage,"Error",JOptionPane.ERROR_MESSAGE);
            frame.setVisible(false);
            new HomePage();
            frame.dispose();

        }
    }
}
