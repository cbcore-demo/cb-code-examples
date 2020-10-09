
procedure 'Poll for PR Merge', {
  projectName = 'Custom008'
  timeLimitUnits = 'minutes'

  formalParameter 'Configuration', defaultValue: null, {
    description = 'Bitbucket configuration'
    expansionDeferred = '0'
    required = '1'
  }

  formalParameter 'NumberOfChecks', defaultValue: null, {
    description = 'The number of times to check for the desired state before giving up'
    expansionDeferred = '0'
    required = '1'
  }

  formalParameter 'PollingInterval', defaultValue: '60', {
    description = 'Polling Interval in seconds'
    expansionDeferred = '0'
    required = '0'
  }

  formalParameter 'prId', defaultValue: null, {
    description = 'PR to be queried'
    expansionDeferred = '0'
    required = '1'
  }

  formalParameter 'TargetState', defaultValue: null, {
    description = 'PR desired end state (e.g. MERGED)'
    expansionDeferred = '0'
    required = '1'
  }

  step 'Get State', {
    alwaysRun = '0'
    broadcast = '0'
    command = '''import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.ActualParameter
ElectricFlow ef = new ElectricFlow()

def PollingInterval = $[PollingInterval]
         def Iteration = 1
         def ApprovalStatus = \"\"

GetApprovalStatus = {
\tdef params = [
\t\tnew ActualParameter(\'config_name\', \'$[Configuration]\'),
\t\tnew ActualParameter(\'outputProperty\', \'/myJob/prState\'),
\t\tnew ActualParameter(\'prId\', \'$[prId]\'),
\t]
\tdef RunResponse = ef.runProcedure procedureName: \'Get PR Status\', projectName: \'$[/myProject/name]\', \t\tactualParameters: params
\tdef JobId = RunResponse.jobId
\tef.setProperty propertyName: \"/myJob/report-urls/Get PR Status Job\", value: \"link/jobDetails/jobs/${JobId}\"

\t// Wait for job
\tdef JobStatus
\twhile ((JobStatus = (String) ef.getJobStatus(jobId: JobId).status) != \"completed\") {
\t\tprintln \"Job status: \" + JobStatus
\t\tsleep 5000 // 5 seconds
\t}

             ApprovalStatus = ef.getProperty(propertyName: \"/myJob/prState\", jobId: JobId).property.value
\t//ef.setProperty propertyName: \"/myJob/report-urls/Pull Request: $[prId]\", value: \"$ApprovalStatus\"
\tprintln \"PR status: $ApprovalStatus\"
\treturn ApprovalStatus
}

         println \"Checking PR $[prId] for status $[TargetState].  Polling interval is set to $[PollingInterval] seconds and there will be a total of $[NumberOfChecks] checks before failing\"
         println \"Check #1\"

while (GetApprovalStatus() != \'$[TargetState]\' && Iteration < $[NumberOfChecks]) {
\tsleep PollingInterval * 1000
             Iteration = Iteration + 1
             println \"Check #\" + Iteration
}

         if (ApprovalStatus != \'$[TargetState]\') {
             println \"Desired Target State of $[TargetState] was not met.  Current State is $ApprovalStatus\"
             System.exit(-1)
         }

         println \"Desired Target State of $[TargetState] was met.\"

'''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    parallel = '0'
    projectName = 'Custom008'
    releaseMode = 'none'
    shell = 'ec-groovy'
    timeLimitUnits = 'minutes'
  }

  // Custom properties
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Configuration</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-Rest plugin</documentation>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

 <formElement>
    <property>NumberOfChecks</property>
    <label>Number of Checks</label>
    <documentation>The number of times to check the PR state before aborting.  Default is 10.</documentation>
    <type>entry</type>
    <default>10</default>
    <required>0</required>
  </formElement>

  <formElement>
    <property>PollingInterval</property>
    <label>Polling Interval</label>
    <documentation>The gap between checks in seconds.  Default is 60 seconds.</documentation>
    <type>entry</type>
    <default>60</default>
    <required>0</required>
  </formElement>

  <formElement>
    <property>prId</property>
    <label>Pull Request ID</label>
    <documentation>The ID of the Pull Request to query.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TargetState</property>
    <label>Target State</label>
    <documentation>The PR state to wait for (e.g. MERGED)</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}
