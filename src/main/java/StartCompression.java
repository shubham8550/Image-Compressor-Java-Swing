import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.swing.*;

public class StartCompression {

    StartCompression(JFrame frame,String location) {
        try {
            File input = new File(location);
            BufferedImage image = ImageIO.read(input);


            File compressedImageFile = new File("compressed_image.jpg");
            OutputStream os = new FileOutputStream(compressedImageFile);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(FilenameUtils.getExtension(location));
            ImageWriter writer = (ImageWriter) writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.05f);  // Change the quality value you prefer
            writer.write(null, new IIOImage(image, null, null), param);

            os.close();
            ios.close();
            writer.dispose();
        }catch (Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(frame,"Unable to compress Image ","Error",JOptionPane.ERROR_MESSAGE);
            frame.setVisible(false);
            new HomePage();
            frame.dispose();

        }
    }
}
