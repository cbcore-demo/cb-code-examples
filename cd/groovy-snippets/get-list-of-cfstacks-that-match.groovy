import com.electriccloud.client.groovy.ElectricFlow
import groovy.xml.*

ElectricFlow ef = new ElectricFlow()

def ListStacksResponse = new XmlSlurper().parseText('''$[/myStageRuntime/tasks/ListStacks/job/outputParameters/result]''')

List options = ListStacksResponse.'**'.findAll{ node -> node.name() == 'StackName' }*.text()

println options

options.each {
    def thisitem = it
	if ( thisitem.contains("pcherry") ) {
    	println "Setting property /myStageRuntime/cfstacks/" + thisitem + " with value " + thisitem
	    ef.setProperty propertyName: '/myStageRuntime/cfstacks/' + thisitem, value: thisitem
    }
}