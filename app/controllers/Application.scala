package controllers

import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def createRecord = Action(parse.json) { request =>
    Logger.info(s"Received Notification: " + Json.prettyPrint(request.body))
    NoContent
  }
}