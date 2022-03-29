def projectNameStr = "pcherry"
def propertyToGet = "Test"
def result = "Environment | Property " + propertyToGet + "\n"
getEnvironments(projectName: projectNameStr).each {
  val = getProperty(propertyName: "/projects/" + projectNameStr + "/environments/" + it.name + "/" + propertyToGet).value
  result += it.name + " | " + val + "\n"
}

result