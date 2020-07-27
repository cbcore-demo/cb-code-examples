import com.electriccloud.client.groovy.ElectricFlow
import java.util.regex.Pattern

ElectricFlow ef = new ElectricFlow()

def buidLog = ef.getProperty propertyName: "/myPipelineRuntime/buildOutput"

// pattern explanation
// ?m means to allow matching across multipe lines
// ie to include beginning (^) and end ($) of the lines
// ?s allows new lines to be matched by . (dot)
// \d+ any number of numbers
def pattern = /(?ms)Results:.*Tests run: (\d+), Failures: (\d+), Errors: (\d+), Skipped: (\d+)/
def partial = buidLog =~ pattern

println "Run value is  [" + partial[0][1] + "]"
println "Failures value is: [" + partial[0][2] + "]"
println "Errors value is [" + partial[0][3] + "]"
println "Skipped value is [" + partial[0][4] + "]"

ef.setProperty propertyName: "/myPipelineRuntime/testsRun", value: partial[0][1]
ef.setProperty propertyName: "/myPipelineRuntime/testsFailures", value: partial[0][2]
ef.setProperty propertyName: "/myPipelineRuntime/testsErrors", value: partial[0][3]
ef.setProperty propertyName: "/myPipelineRuntime/testsSkipped", value: partial[0][4]
