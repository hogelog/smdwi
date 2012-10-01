package models

import org.specs2.mutable.Specification

import play.api.test._
import play.api.test.Helpers._
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: hogelog
 * Date: 12/10/02
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
object PageSpec extends Specification {
  "page model" should {
    "page toHtml" in {
      val html = Page("hello", "#h1\nHello!").toHtml
      html must startWith("<h1")
      html must endWith("<p>Hello!</p>")
    }

    "page create" in {
      val page = Page.create(Page("hello", "#h1\nHello!"))
      val file = new File("target/pages/" + page.name)
      file must exist
    }

    "page load" in {
      Page.create(Page("hello", "#h1\nHello!"))
      val page = Page.findByName("hello")
      page must beSome
      val html = page.get.toHtml
      html must be startWith("<h1")
      html must be endWith("<p>Hello!</p>")
    }
  }
}
