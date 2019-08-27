package com.shu.schema

import com.shu.models.Property
import com.shu.repositories.PropertyRepo
import sangria.schema._
import sangria.macros.derive._

object PropertySchema {

  val PropertyType = ObjectType(
    "Property",
    "The property details",
    fields[Unit, Property](
      Field("id", StringType, resolve = _.value.id),
      Field("slug", StringType, resolve = _.value.slug),
      Field("postcode", IntType, resolve = _.value.postcode),
    )
  )

  val PropertyType2 = deriveObjectType[Unit, Property](
    ObjectTypeDescription("The property details")
  )

  val Id = Argument("id", StringType)

  val QueryType = ObjectType("Query", fields[PropertyRepo, Unit](
    Field("property", OptionType(PropertyType),
      description = Some("Returns a property with specific `id`"),
      arguments = Id :: Nil,
      resolve = c => c.ctx.property(c arg Id)
    ),
    Field("properties", ListType(PropertyType),
      description = Some("Returns a list of all available properties"),
      resolve = _.ctx.properties
    )
  ))

  val schema = Schema(QueryType)

}
