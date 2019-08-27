package com.shu.repositories

import com.shu.models.Property

class PropertyRepo {

  private val propertiesList = List(
    Property("1", "hawthorn-vic-3130", 3130),
    Property("2", "north-melbourne-vic-3510", 3510),
    Property("3", "glen-waverley-vic-3150", 3150),
  )

  def property(id: String): Option[Property] =
    propertiesList find (_.id == id)

  def properties: List[Property] = properties

}
