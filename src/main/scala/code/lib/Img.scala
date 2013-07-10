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
object Img {

  def scaleWh(width: Int, height: Int, maxWidth: Int = 400, maxHeight: Int = 300): (Int, Int) = {
    /*if (width <= maxWidth && height <= maxHeight) {
      return (width, height)
    }*/
    val p = 4 / 3
    if (width > height) (maxWidth, maxHeight * p) else (maxWidth * p, maxHeight)
  }

  def main(args: Array[String]): Unit = {

    /*val fileName = "d:\\tmp\\10600116.jpg.png";
    val fileName = "d:\\tmp\\14742309.jpg"
    val fileName = "d:\\tmp\\14742309.jpg"*/
    val fileName = "d:\\tmp\\mm.jpg"
    var in = new FileInputStream(new File(fileName))
    var out = new FileOutputStream(new File("d:\\tmp\\rest.png"))

    val oimg = Image(in)
    val swh = (oimg.width, oimg.height)
    val wh = scaleWh(swh._1, swh._2)
    MyImage.myFit(oimg, wh, swh).write(out)
    out.close()
  }
}