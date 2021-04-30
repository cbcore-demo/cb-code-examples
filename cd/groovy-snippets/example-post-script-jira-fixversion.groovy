import groovy.json.* 
import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
File file = new File(args[0])
String fileContent = file.text
def jsonSlurper = new JsonSlurper()
def RestResponse = jsonSlurper.parseText(fileContent)
def JobId = ef.getProperty(propertyName: "/myJob/jobId").property.value
def FormContentJson = ef.getProperty(propertyName: "/myJob/formContent").property.value
def FormContent = jsonSlurper.parseText(FormContentJson)
def BaseUrl = RestResponse.self.split("/")[0,1,2].join("/")
def NewUrl = [BaseUrl,"projects","${FormContent.project}","versions","${RestResponse.id}"].join("/")
ef.setProperty(propertyName: "/myStageRuntime/ec_summary/Fix Version",
	value: """								<html><a href="${NewUrl}" target="_blank">${RestResponse.name}</a></html>
	""".stripIndent(),
	jobId: JobId)
ef.setProperty(propertyName: "/myPipelineRuntime/FixVersion",
	value: RestResponse.name,
	jobId: JobId)