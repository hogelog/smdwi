package models

import io.{Source, Codec}
import java.nio.charset.Charset
import scalax.io.Resource
import java.io.{IOException, File}
import org.fusesource.scalamd.Markdown

case class Page(name: String, markdown: String) {
  def toHtml = Markdown(markdown)
}

object Page {
  val pagesDir = new File("target/pages")
  checkDirectory(pagesDir)

  implicit val codec:Charset = Codec.UTF8

  def nameFile(name: String) = new File(pagesDir, name)

  def checkDirectory(dir: File) = if (!dir.exists && !dir.mkdirs)
    throw new IOException("cannot create directory: " + dir)

  def load(file: File, name: String): Page = {
    val source = Source.fromFile(file)(codec)
    val markdown = source.mkString
    Page(name, markdown)
  }

  def create(page: Page): Page = {
    val file = nameFile(page.name)
    checkDirectory(file.getParentFile)

    val resource = Resource.fromFile(file)
    resource.write(page.markdown)(codec)
    page.copy()
  }

  def findByName(name: String): Option[Page] = {
    val file = nameFile(name)
    if (file.exists) Some(load(file, name)) else None
  }

}
