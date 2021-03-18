import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.ActualParameter
import groovy.json.JsonSlurper

ElectricFlow ef = new ElectricFlow()
def jsonSlurper = new JsonSlurper()

def PollingInterval = $[PollingInterval]
def Iteration = 1
def ApprovalStatus = ""

GetApprovalStatus = {
	def params = [
		new ActualParameter('config_name', '$[Configuration]'),
		new ActualParameter('property_sheet', '/myJob'),
		new ActualParameter('record_id', '$[crId]'),
	]
	def RunResponse = ef.runProcedure procedureName: 'GetRecord', projectName: '$[/plugins/EC-ServiceNow/projectName]', actualParameters: params
	def JobId = RunResponse.jobId
	ef.setProperty propertyName: "/myJob/report-urls/Get CR Status Job", value: "link/jobDetails/jobs/${JobId}"

	// Wait for job
	def JobStatus
	while ((JobStatus = (String) ef.getJobStatus(jobId: JobId).status) != "completed") {
		println "Job status: " + JobStatus
		sleep 5000 // 5 seconds
	}

             def responseAsString = ef.getProperty(propertyName: "/myJob/ResponseContent", jobId: JobId).property.value
             def responseAsJson = jsonSlurper.parseText(responseAsString)
	//ef.setProperty propertyName: "/myJob/report-urls/Change Request: $[crId]", value: "$ApprovalStatus"
            ApprovalStatus = responseAsJson.approval[0]
	println "CR status: $ApprovalStatus"
	return ApprovalStatus
}

         println "Checking CR $[crId] for status $[TargetState].  Polling interval is set to $[PollingInterval] seconds and there will be a total of $[NumberOfChecks] checks before failing"
         println "Check #1"

while (GetApprovalStatus() != '$[TargetState]' && Iteration < $[NumberOfChecks]) {
	sleep PollingInterval * 1000
             Iteration = Iteration + 1
             println "Check #" + Iteration
}

         if (ApprovalStatus != '$[TargetState]') {
             println "Desired Target State of $[TargetState] was not met.  Current State is $ApprovalStatus"
             System.exit(-1)
         }

         println "Desired Target State of $[TargetState] was met."

