package controllers

import play.api.mvc._
import play.api.libs.json
import play.api._
import com.sun.xml.internal.bind.v2.TODO
import libs.json.JsError

/**
 * Created with IntelliJ IDEA.
 * User: igobrilhante
 * Date: 20/06/13
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
object RecomendacaoController extends Controller {


  def test() = Action(parse.json) {  request =>
    request.body.validate[String].map{
      case name => Ok("Hello " + name)
    }.recoverTotal{
      e => BadRequest("Detected error:"+ JsError.toFlatJson(e))
    }

  }

  def get(id : Long) = TODO

  def avaliar(id : Long, avaliacao : Double) = TODO

  def get(hourOfDay : Int, weather : String, lat : Double, lng : Double ) = TODO

}
