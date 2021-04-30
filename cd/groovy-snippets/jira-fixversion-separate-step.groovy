import groovy.json.* 
import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
def jsonSlurper = new JsonSlurper()

def RawRestResponse = ef.getProperty(propertyName: "/myStageRuntime/tasks/Create Fix Version/job/RestResponse").property.value

def RestResponse = jsonSlurper.parseText(RawRestResponse)

def JobId = ef.getProperty(propertyName: "/myJob/jobId").property.value
ef.setProperty(propertyName: "/myStageRuntime/ec_summary/Fix Version",
	value: """								<html><a href="${RestResponse.self}" target="_blank">${RestResponse.name}</a></html>
	""".stripIndent(),
	jobId: JobId)
ef.setProperty(propertyName: "/myPipelineRuntime/FixVersion",
	value: RestResponse.name,
	jobId: JobId)