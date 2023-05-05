package lib;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;

public class ImageLoader {
    
    public static final String relRoot = System.getProperty("user.home") + "/Elevens/assets";

    public static Image loadImage(String relPath) {
        return new ImageIcon(new File(relRoot + relPath).getAbsolutePath()).getImage();
    }

}
