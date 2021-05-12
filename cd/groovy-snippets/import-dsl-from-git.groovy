import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*

ElectricFlow ef = new ElectricFlow()

// define input parameters for import job
def params = [
    new ActualParameter(actualParameterName: 'config', value: 'GitHub CloudBees Guru'),
    new ActualParameter(actualParameterName: 'GitRepo', value: 'https://github.com/cloudbees-guru/cd-examples-library.git'),
    new ActualParameter(actualParameterName: 'GitBranch', value: 'main'),
    new ActualParameter(actualParameterName: 'rsrcName', value: 'local'),
    new ActualParameter(actualParameterName: 'dest', value: '/tmp/import-dsl-from-git'),
    new ActualParameter(actualParameterName: 'relPath', value: 'cd-project-export'),
    new ActualParameter(actualParameterName: 'overwrite', value: 'true'),
    new ActualParameter(actualParameterName: 'cleanup', value: 'true')]

// run the import job passing in the input parameters
def result = ef.runProcedure(
          projectName: "/plugins/EC-DslDeploy/project",
          procedureName: "importDslFromGit",
          actualParameters: params)

// get the id of the new job
def JobId = result.jobId

// create link in job report to that created job
ef.setProperty propertyName: "/myJob/report-urls/Import DSL From Git Job", value: "https://cbcd-examples.cloudbees.guru/commander/link/jobDetails/jobs/$JobId"

// update log with jobId
println "Started import job with id " + JobId

// wait for import job to complete
def JobStatus
while ((JobStatus = (String) ef.getJobStatus(jobId: JobId).status) != "completed") {
    println "Job status: " + JobStatus
	sleep 5000 // 5 seconds
}

// Once the import job is completed, get the outcome
def JobOutcome = (String) ef.getJobStatus(jobId: JobId).outcome
println "Import job completed with status " + JobStatus + " and outcome " + JobOutcome // Display outcome in the log
    
// Check if outcome was success
if (JobOutcome != "success") {
	System.exit(-1) // if not success, then end job with fail state
} else {
	System.exit(0) // exit with success
}