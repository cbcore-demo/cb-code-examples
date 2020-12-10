// This code can be used when you have a procedure you want to call that could fail
// e.g. you want to test for the presence of a file and decide what to do
// Use this if you don't want the step to fail and therefore the pipeline to fail
import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.ActualParameter

ElectricFlow ef = new ElectricFlow()

// Build the parameters to pass to the subprocedure including the credential
def params = [
    new ActualParameter('inputval', 'Pass')
]

// Run the subprocedure and keep the jobId
def JobId = ef.runProcedure(procedureName: "Pass or Fail Proc", projectName: "Custom006", actualParameters: params).jobId

// Create job link to spawned job in the job report
ef.setProperty propertyName: "/myJob/report-urls/Called procedure job", value: "link/jobDetails/jobs/${JobId}"

// Wait for job
while ((JobStatus = (String) ef.getJobStatus(jobId: JobId).status) != "completed") {
    println "Job status: " + JobStatus
    sleep 2000
}

// Grab the status of the called procedure
def result = ef.getJobInfo(jobId: JobId).job.outcome

// Show the status in the job log
println "Subprocedure resulted in " + result

// Set a property value based on the status of that job
if (result == "success") {
    ef.setProperty(propertyName: "/myJob/s3bucketexists", value: "TRUE")
} else {
    ef.setProperty(propertyName: "/myJob/s3bucketexists", value: "FALSE")
}

// Now you can place a condition on following steps
// eg $[/javascript myStageRuntime.tasks["Run Proc (Fail)"].job.s3bucketexists == "FALSE"]