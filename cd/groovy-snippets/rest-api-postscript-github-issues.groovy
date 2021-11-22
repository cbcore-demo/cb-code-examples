// postscript for REST API call for Status-Examples-Library pipeline
import groovy.json.*
import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
File file = new File(args[0])
String fileContent = file.text
def jsonSlurper = new JsonSlurper()
def RestResponse = jsonSlurper.parseText(fileContent)

// Number of open issues
List openIssues = RestResponse.findAll { it.state == 'open' }.number
openIssuesNum = openIssues.size()
ef.setProperty(propertyName: "/myStageRuntime/openIssues", value: "$openIssuesNum")

// Number of unassigned open issues
List openIssuesUnAssigned = RestResponse.findAll { it.assignee == null }.number
openIssuesUnAssignedNum = openIssuesUnAssigned.size()
ef.setProperty(propertyName: "/myStageRuntime/openIssuesUnAssigned", value: "$openIssuesUnAssignedNum")

// Number of open issues of type documentation
List openIssuesDocumentation = RestResponse.findAll { it.labels.findAll { label -> label.name == 'documentation' } }.number
openIssuesDocumentationNum = openIssuesDocumentation.size()
ef.setProperty(propertyName: "/myStageRuntime/openIssuesDocumentation", value: "$openIssuesDocumentationNum")

// Number of open issues of type enhaccement
List openIssuesEnhancement = RestResponse.findAll { it.labels.findAll { label -> label.name == 'enhancement' } }.number
openIssuesEnhancementNum = openIssuesEnhancement.size()
ef.setProperty(propertyName: "/myStageRuntime/openIssuesEnhancement", value: "$openIssuesEnhancementNum")

// Number of open issues of type bug
List openIssuesBug = RestResponse.findAll { it.labels.findAll { label -> label.name == 'bug' } }.number
openIssuesBugNum = openIssuesBug.size()
ef.setProperty(propertyName: "/myStageRuntime/openIssuesBug", value: "$openIssuesBugNum")
