// This code can be used in a procedure step where the procedure has an input parameter
// of name 'credentials', and that parameter credential has been attached to this step
import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.ActualParameter
import com.electriccloud.client.groovy.models.Credential

ElectricFlow ef = new ElectricFlow()

// Extract credentials from credential object attached to this step
def userName=ef.getFullCredential(credentialName: "credentials").credential.userName
def password=ef.getFullCredential(credentialName: "credentials").credential.password

// Build a new credential object to pass to the subprocedure
def creds = [new Credential(credentialName: 'credentials', userName: userName, password: password)]

// Build the parameters to pass to the subprocedure including the credential
def params = [
    new ActualParameter('credentials', 'credentials'),
    new ActualParameter('host', '1234'),
    new ActualParameter('ProductParams', 'None')
]

// Run the subprocedure and keep the jobId
def JobId = ef.runProcedure(procedureName: "oc login pass in params", projectName: "Custom012", actualParameters: params, credentials: creds).jobId
// Create job link to spawned job in the job report
ef.setProperty propertyName: "/myJob/report-urls/Called procedure job", value: "link/jobDetails/jobs/${JobId}"
// Wait for job
while ((String) ef.getJobStatus(jobId: JobId).status == "running") {
    println "Job still running"
    sleep 2000
}
// Grab a property from the subprocedure.  This assumes the subprocedure
// has a corresponding setProperty
println ef.getProperty(propertyName: "/myJob/output", jobId: JobId).property.value