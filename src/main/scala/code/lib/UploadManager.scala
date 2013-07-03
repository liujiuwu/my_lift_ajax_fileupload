package code.lib

import java.io.File
import java.io.FileOutputStream
import net.liftweb.common.Box
import net.liftweb.http.FileParamHolder
import net.liftweb.http.InMemoryResponse
import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonDSL._
import net.liftweb.util.StringHelpers
import com.sksamuel.scrimage.Image
import com.sksamuel.scrimage.ScaleMethod._
import net.liftweb.http.JsonResponse

object UploadManager extends RestHelper {
  serve {
    case "uploading" :: Nil Post req => {
      def saveImage(fph: FileParamHolder) = {
        val imageName = StringHelpers.randomString(16)
        val fileName = fph.fileName
        
        val in = fph.fileStream

        var out = new FileOutputStream(new File("d:\\tmp\\" + fileName))
       /* out.write(fph.file)
        out.close()*/
        println(fileName)
        
        //Image(in).resize(100).write(output)
        Image(in).write(out) 
        out.close()
        
        /*MongoStorage.mongoGridFS(fph.fileStream)(fh =>
          { fh.filename = imageName; fh.contentType = fph.mimeType })*/

        ("name" -> fileName) ~ ("type" -> fph.mimeType) ~ ("size" -> fph.length)
      }

      val ojv: Box[JValue] = req.uploadedFiles.map(fph => saveImage(fph)).headOption
      val ajv = ("name" -> "n/a") ~ ("type" -> "n/a") ~ ("size" -> 0L)
      val ret = ojv openOr ajv

      val jr = JsonResponse(ret).toResponse.asInstanceOf[InMemoryResponse]
      InMemoryResponse(jr.data, ("Content-Length", jr.data.length.toString) ::
        ("Content-Type", "text/plain") :: Nil, Nil, 200)
    }

    /*case "serving" :: imageName :: Nil Get req => {
      MongoStorage.mongoGridFS.findOne(imageName) match {
        case Some(image) =>
          val imageStream = image.inputStream
          StreamingResponse(imageStream, () => imageStream.close(), image.length, ("Content-Type", image.contentType.get) :: Nil, Nil, 200)
        case _ => new BadResponse
      }
    }*/
  }
}
