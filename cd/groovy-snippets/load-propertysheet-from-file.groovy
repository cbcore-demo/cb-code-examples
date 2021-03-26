// Example yaml File
// - name: Option 1
//   value: option1
// - name: Option 2
//   value: option2
// - name: Option 3
//   value: option3

import com.electriccloud.client.groovy.ElectricFlow
import org.yaml.snakeyaml.Yaml

ElectricFlow ef = new ElectricFlow()
Yaml parser = new Yaml()

def propertypath = "/myProject/dynamicoptions/"
def items = parser.load(("/tmp/dynamicoptions.yaml" as File).text)
List results = items.name

// Delete existing properties
//def props = ef.getProperties(projectName: "$[/myProject]", propertyName: "dynamicoptions").propertySheet.property.each { prop ->
def props = ef.getProperties(propertyName: propertypath).propertySheet.property.each { prop ->
  println "Deleting " + propertypath + prop.propertyName
  ef.deleteProperty(propertyName: propertypath +prop.propertyName)
}

// Load new properties  into propertySheet
results.each {
    def thisitem = it
    def thisvalue = items.find { it.name == thisitem }.value
    println "Setting property " + propertypath + thisitem + " with value " + thisvalue
    ef.setProperty propertyName: propertypath +thisitem, value: thisvalue
}

println "New values set"