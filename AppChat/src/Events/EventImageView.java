package Events;

import java.awt.image.BufferedImage;
import javax.swing.Icon;

public interface EventImageView {

    public void viewImage(Icon image,BufferedImage bImage, String filename);

    public void saveImage(Icon image, BufferedImage bImage, String filename);
}
