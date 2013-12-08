name := "CMUSDS"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa, 
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
)     

play.Project.playJavaSettings
