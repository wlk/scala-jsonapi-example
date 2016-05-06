import org.zalando.jsonapi.JsonapiRootObjectFormat
import org.zalando.jsonapi.model.JsonApiObject.{NumberValue, StringValue}
import org.zalando.jsonapi.model.RootObject.ResourceObject
import org.zalando.jsonapi.model.{Attribute, Links, RootObject}

trait JsonFormats {

  implicit val userJsonapiFormat = new JsonapiRootObjectFormat[User] {
    override def toJsonapi(u: User): RootObject = {
      RootObject(
        data = Some(
          ResourceObject(
            `type` = "user",
            id = Some(u.id.toString),
            attributes = Some(List(
              Attribute("name", StringValue(u.name)),
              Attribute("id", NumberValue(u.id))
            )),
            relationships = None
          )
        ),
        links = Some(List(Links.Self(s"/user/${u.id}")))
      )
    }

    override def fromJsonapi(rootObject: RootObject): User = ???
  }

}
