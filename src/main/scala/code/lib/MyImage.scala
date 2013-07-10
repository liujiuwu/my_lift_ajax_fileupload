package code.lib

import com.sksamuel.scrimage.Image
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import com.sksamuel.scrimage.ImageTools
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.ScaleMethod._
import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.Position._

object MyImage {
  def myFit(img: Image, t: (Int, Int), source: (Int, Int)): Image = {
    val targetWidth = t._1
    val targetHeight = t._2
    val fittedDimensions = dimensionsToFit((targetWidth, targetHeight), (source._1, source._2))
    val scaled = img.scaleTo(fittedDimensions._1, fittedDimensions._2)
    val target = Image.filled(targetWidth, targetHeight, java.awt.Color.WHITE)
    val g2 = target.awt.getGraphics.asInstanceOf[Graphics2D]
    val x = ((targetWidth - fittedDimensions._1) / 2.0).toInt
    val y = ((targetHeight - fittedDimensions._2) / 2.0).toInt
    g2.drawImage(scaled.awt, x, y, null)
    g2.dispose()
    target
  }

  def dimensionsToFit(target: (Int, Int), source: (Int, Int)): (Int, Int) = {
    val maxWidth = if (target._1 == 0) source._1 else target._1
    val maxHeight = if (target._2 == 0) source._2 else target._2

    val wscale = maxWidth / source._1.toDouble
    val hscale = maxHeight / source._2.toDouble

    if (source._1 < target._1 && source._2 < target._2) {
      return source
    }

    if (wscale < hscale)
      ((source._1 * wscale).toInt, (source._2 * wscale).toInt)
    else
      ((source._1 * hscale).toInt, (source._2 * hscale).toInt)
  }
}