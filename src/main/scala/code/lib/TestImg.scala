package code.lib

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import com.sksamuel.scrimage.Image
import com.sksamuel.scrimage.Format._
import javax.imageio.ImageIO
import net.coobird.thumbnailator.Thumbnails
import java.awt.Point
import net.coobird.thumbnailator.geometry.Positions
import com.sksamuel.scrimage.Position._
object TestImg {

  def main(args: Array[String]): Unit = {

    var in = new FileInputStream(new File("d:\\tmp\\10600116.jpg.png"))
    var out = new FileOutputStream(new File("d:\\tmp\\rest.jpg"))
    var in2 = new FileInputStream(new File("d:\\tmp\\rest.jpg"))
    var out2 = new FileOutputStream(new File("d:\\tmp\\rest2.jpg"))

    val originalImage = ImageIO.read(in)
    val height = originalImage.getHeight
    val width = originalImage.getWidth
    val st = g(width, height)
    if (width > 400 && height > 300) {
      Image(originalImage).fit(400, 300).write(out)
    } else {
      Image(originalImage).write(out)
    }
    //Image(in2).fit(320, 200).write(out2)
    out2.close()
    out.close()

    Thumbnails.of("d:\\tmp\\rest.jpg")
      .size(320, 200).sourceRegion(10, 40, 320, 200)
      .outputQuality(1f)
      .toFile(new File("d:\\tmp\\rest3.jpg"));

    //val buf = ImageResizer.resize(in, 200, 150)
    //ImageIO.write(buf, "jpg", out)

    /*Thumbnails.of(new File("d:\\tmp\\14742313.jpg"))
      .size(400, 400)
      .outputQuality(1f)
      .toFile(new File("d:\\tmp\\rest.jpg"));
    
    Thumbnails.of(new File("d:\\tmp\\rest.jpg"))
      .size(200, 150)
      .sourceRegion(10, 20, 200, 150)  
      .outputQuality(1f)
       //.watermark(Positions.CENTER, ImageIO.read(new File("d:\\tmp\\ajax-loader.gif")), 0.5f)
      .toFile(new File("d:\\tmp\\rest2.jpg"));
    
    Thumbnails.of(new File("d:\\tmp\\rest.jpg"))
      .size(100, 50)
      .sourceRegion(10, 20, 100, 50)  
      .outputQuality(1f)
       //.watermark(Positions.CENTER, ImageIO.read(new File("d:\\tmp\\ajax-loader.gif")), 0.5f)
      .toFile(new File("d:\\tmp\\rest3.jpg"));*/

    /* Thumbnails.of(in)
      .size(400, 300).sourceRegion(0,0, 400, 300)
      .outputQuality(1f)
      .toFile(new File("d:\\tmp\\rest.jpg"));
*/
  }

  def g(width: Int, height: Int, maxWidth: Int = 400, maxHeight: Int = 300): (Int, Int) = {
    if (width <= maxWidth && height <= maxHeight) {
      return (width, height)
    }

    var scaledWidth = maxWidth
    var scaledHeight = maxHeight
    if (width > height) {
      scaledWidth = maxWidth
      scaledHeight = height * scaledWidth / width
    } else {
      scaledHeight = maxHeight
      scaledWidth = width * scaledHeight / height
    }
    (scaledWidth, scaledHeight)
  }

}