import groovy.json.* 
import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
File file = new File(args[0])
String fileContent = file.text
def jsonSlurper = new JsonSlurper()
def RestResponse = jsonSlurper.parseText(fileContent)
def JobId = ef.getProperty(propertyName: "/myJob/jobId").property.value
ef.setProperty(propertyName: "/myStageRuntime/ec_summary/Fix Version",
	value: """								<html><a href="${RestResponse.self}" target="_blank">${RestResponse.name}</a></html>
	""".stripIndent(),
	jobId: JobId)
ef.setProperty(propertyName: "/myPipelineRuntime/FixVersion",
	value: RestResponse.name,
	jobId: JobId)
