def projectNameStr = "pcherry"
def propertyToGet = "Test"
getEnvironments(projectName: projectNameStr).each {
  val = getProperty(propertyName: "/projects/" + projectNameStr + "/environments/" + it.name + "/" + propertyToGet).value
  println "Environment " + it.name + " " + propertyToGet + " = " + val
}