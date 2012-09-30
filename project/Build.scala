import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "smdwi"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.fusesource.scalamd" % "scalamd" % "1.5"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
