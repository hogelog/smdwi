package controllers

import play.api._
import http.ContentTypeOf
import play.api.mvc._
import models.Page
import templates.Html

object Application extends Controller {
  
  def index = Action {
    Page.findByName("index") match {
      case Some(page:Page) => Ok(page.toHtml).as("text/html")
      case None => NotFound
    }
  }
  
}
