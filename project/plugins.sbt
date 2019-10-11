resolvers ++= Seq(
  Resolver.url(
    "scalasbt",
    new URL(
      "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"
    )
  )(Resolver.ivyStylePatterns),
  "ibiblio" at "http://mirrors.ibiblio.org/pub/mirrors/maven2"
)

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.6")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.4.2")
