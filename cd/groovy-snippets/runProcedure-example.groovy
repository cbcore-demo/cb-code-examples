import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*

ElectricFlow ef = new ElectricFlow()

def params = [
    new ActualParameter(actualParameterName: 'config', value: 'GitHub CloudBees Guru'),
    new ActualParameter(actualParameterName: 'GitRepo', value: 'https://github.com/cloudbees-guru/cd-examples-library.git'),
    new ActualParameter(actualParameterName: 'GitBranch', value: 'main'),
    new ActualParameter(actualParameterName: 'rsrcName', value: 'local'),
    new ActualParameter(actualParameterName: 'dest', value: '/tmp/import-dsl-from-git'),
    new ActualParameter(actualParameterName: 'relPath', value: 'cd-project-export'),
    new ActualParameter(actualParameterName: 'overwrite', value: 'true'),
    new ActualParameter(actualParameterName: 'cleanup', value: 'true')]

def result = ef.runProcedure(
          projectName: "/plugins/EC-DslDeploy/project",
          procedureName: "importDslFromGit",
          actualParameters: params)

def JobId = result.jobId

ef.setProperty propertyName: "/myJob/report-urls/Import DSL From Git Job", value: "https://cbcd-examples.cloudbees.guru/commander/link/jobDetails/jobs/$JobId"

println "Started import job with id " + JobId

def JobStatus
while ((JobStatus = (String) ef.getJobStatus(jobId: JobId).status) != "completed") {
    println "Job status: " + JobStatus
	sleep 5000 // 5 seconds
}