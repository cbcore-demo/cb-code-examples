procedure 'Poll ServiceNow CR status', {
  timeLimitUnits = 'minutes'

  formalParameter 'Configuration', {
    description = 'ServiceNow configuration'
    expansionDeferred = '0'
    required = '1'
  }

  formalParameter 'crId', {
    description = 'CR to be queried'
    expansionDeferred = '0'
    required = '1'
  }

  formalParameter 'NumberOfChecks', {
    description = 'The number of times to check for the desired state before giving up'
    expansionDeferred = '0'
    required = '1'
  }

  formalParameter 'PollingInterval', defaultValue: '60', {
    description = 'Polling Interval in seconds'
    expansionDeferred = '0'
    required = '0'
  }

  formalParameter 'TargetState', {
    description = 'CR desired end state (e.g. APPROVED)'
    expansionDeferred = '0'
    required = '1'
  }

  step 'Get State', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.ActualParameter
import groovy.json.JsonSlurper

ElectricFlow ef = new ElectricFlow()
def jsonSlurper = new JsonSlurper()

def PollingInterval = $[PollingInterval]
def Iteration = 1
def ApprovalStatus = ""

GetApprovalStatus = {
	def params = [
		new ActualParameter(\'config_name\', \'$[Configuration]\'),
		new ActualParameter(\'property_sheet\', \'/myJob\'),
		new ActualParameter(\'record_id\', \'$[crId]\'),
	]
	def RunResponse = ef.runProcedure procedureName: \'GetRecord\', projectName: \'$[/plugins/EC-ServiceNow/projectName]\', actualParameters: params
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

while (GetApprovalStatus() != \'$[TargetState]\' && Iteration < $[NumberOfChecks]) {
	sleep PollingInterval * 1000
             Iteration = Iteration + 1
             println "Check #" + Iteration
}

         if (ApprovalStatus != \'$[TargetState]\') {
             println "Desired Target State of $[TargetState] was not met.  Current State is $ApprovalStatus"
             System.exit(-1)
         }

         println "Desired Target State of $[TargetState] was met."

'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    releaseMode = 'none'
    resourceName = ''
    shell = 'ec-groovy'
    subprocedure = ''
    subproject = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''
  }

  // Custom properties
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Configuration</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-ServiceNow plugin</documentation>
    <propertyReference>/plugins/EC-ServiceNow/project/ServiceNow_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

 <formElement>
    <property>NumberOfChecks</property>
    <label>Number of Checks</label>
    <documentation>The number of times to check the CR state before aborting.  Default is 10.</documentation>
    <type>entry</type>
    <value>10</value>
    <required>0</required>
  </formElement>

  <formElement>
    <property>PollingInterval</property>
    <label>Polling Interval</label>
    <documentation>The gap between checks in seconds.  Default is 60 seconds.</documentation>
    <type>entry</type>
    <value>60</value>
    <required>0</required>
  </formElement>

  <formElement>
    <property>crId</property>
    <label>Change Request ID</label>
    <documentation>The ID of the Change Request to query.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TargetState</property>
    <label>Target State</label>
    <documentation>The CR state to wait for (e.g. APPROVED)</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}