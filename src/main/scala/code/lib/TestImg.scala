package code.lib

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

import com.sksamuel.scrimage.Image
import com.sksamuel.scrimage.Format._

import javax.imageio.ImageIO

object TestImg {

  def main(args: Array[String]): Unit = {

    var in = new FileInputStream(new File("d:\\tmp\\mm.jpg"))
    var out = new FileOutputStream(new File("d:\\tmp\\rest.jpg"))

    val maxWidth = 200
    val maxHeight = 350

    val originalImage = ImageIO.read(in)

    val height = originalImage.getHeight
    val width = originalImage.getWidth

    if (width <= maxWidth && height <= maxHeight) {
      Image(originalImage).write(out)
      out.close()
      return
    }
    
    var scaledWidth = width
    var scaledHeight = height
    val ratio: Double = width / height
    if (scaledWidth > maxWidth) {
      scaledWidth = maxWidth
      scaledHeight = (scaledWidth.doubleValue / ratio).intValue
    }
    if (scaledHeight > maxHeight) {
      scaledHeight = maxHeight
      scaledWidth = (scaledHeight.doubleValue * ratio).intValue
    }

    Image(originalImage).scaleTo(scaledWidth, scaledHeight).write(out)
    out.close()

    //val buf = ImageResizer.resize(in, 200, 150)
    //ImageIO.write(buf, "jpg", out)

  }

}