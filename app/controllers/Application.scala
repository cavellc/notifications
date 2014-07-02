package controllers

import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

object Application extends Controller {

  val buffer = new scala.collection.mutable.Queue[JsValue]
  val MAX_BUF_SIZE = 10

  def index = Action {
    Ok(Json.toJson(buffer.reverse))
  }

  def reset = Action {
    buffer.clear()
    Ok
  }

  def createRecord = Action(parse.json) { request =>
    Logger.info(s"Received Notification: " + Json.prettyPrint(request.body))

    buffer += request.body
    if (buffer.size > MAX_BUF_SIZE) buffer.dequeue()

    NoContent
  }
}