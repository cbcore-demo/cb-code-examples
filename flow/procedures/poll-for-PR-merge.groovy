/*
CloudBees CD DSL: Poll Bitbucket PR using ec-groovy
This procedure can be used for pipeline gates.
TODO
*/

project "Custom008",{
	procedure "Poll for PR Merge",{
		formalParameter "TargetState", required: true, description: "PR desired end state (e.g. MERGED)"
		formalParameter "Configuration", required: true, description: "Bitbucket configuration"
		formalParameter "PollingInterval", required: false, defaultValue: "60", description: "Polling Interval in seconds"
        formalParameter "NumberOfChecks", required: true, description: "The number of times to check for the desired state before giving up"
		formalParameter "prId", required: true, description: "PR to be queried"

		step 'Get State', shell: 'ec-groovy', command: '''\
			import com.electriccloud.client.groovy.ElectricFlow
			import com.electriccloud.client.groovy.models.ActualParameter
			ElectricFlow ef = new ElectricFlow()

			def PollingInterval = $[PollingInterval]
            def Iteration = 1
            def ApprovalStatus = ""

			GetApprovalStatus = {
				def params = [
					new ActualParameter('config_name', '$[Configuration]'),
					new ActualParameter('outputProperty', '/myJob/prState'),
					new ActualParameter('prId', '$[prId]'),
				]
				def RunResponse = ef.runProcedure procedureName: 'Get PR Status', projectName: '$[/myProject/name]', 		actualParameters: params
				def JobId = RunResponse.jobId
				ef.setProperty propertyName: "/myJob/report-urls/Get PR Status Job", value: "link/jobDetails/jobs/${JobId}"

				// Wait for job
				def JobStatus
				while ((JobStatus = (String) ef.getJobStatus(jobId: JobId).status) != "completed") {
					println "Job status: " + JobStatus
					sleep 5000 // 5 seconds
				}

                ApprovalStatus = ef.getProperty(propertyName: "/myJob/prState", jobId: JobId).property.value
				//ef.setProperty propertyName: "/myJob/report-urls/Pull Request: $[prId]", value: "$ApprovalStatus"
				println "PR status: $ApprovalStatus"
				return ApprovalStatus
			}

            println "Checking PR $[prId] for status $[TargetState].  Polling interval is set to $[PollingInterval] seconds and there will be a total of $[NumberOfChecks] checks before failing."
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

		'''.stripIndent()
	}
}