import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()

// Retrieve credentials from credential object assuming it is attached to the procedure step
// Can be implemented as an input Parameter with name 'credentials'
def userName=ef.getFullCredential(credentialName: "credentials").credential.userName
def password=ef.getFullCredential(credentialName: "credentials").credential.password

def sout = new StringBuilder(), serr = new StringBuilder()  // Prepare to capture stdout and stderr

def command = "oc login $[host] --username $userName --password $password"
println "Running command: " + command

//def proc = command.execute()                 // Call *execute* on the string - simple command
def proc = ['sh','-c', command].execute()     //Call *execute* on the string - non-simple command, shell script etc.
proc.consumeProcessOutput(sout, serr)       // Redirect stdout and stderr
//proc.waitFor()                               // Wait for the command to finish indefinitely
proc.waitForOrKill(600000)              //Wait for the command to finish, up to 10 mins

// Obtain status and output
println "return code: ${ proc.exitValue()}"
println "stderr: $serr"
println "stdout: " + sout

// If the command returned an error code then fail the step
if (proc.exitValue() != 0) {
    System.exit(proc.exitValue())
}

// Store stdout in job property so other steps can use it
ef.setProperty propertyName: "/myJob/output", value: stdout