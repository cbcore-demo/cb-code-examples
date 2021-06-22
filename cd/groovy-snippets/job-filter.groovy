import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*
import java.text.SimpleDateFormat
import groovy.time.TimeCategory

def timenow = new Date()
def timehourago = new Date()

use(TimeCategory) {
	timehourago = timenow - 1.hours
}

ElectricFlow ef = new ElectricFlow()

def sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//println sdf.format(timenow)
println "Listing the jobs that finished since " + sdf.format(timehourago) + " and ended with error status:"

def arg = [[propertyName: 'finish', operator: 'greaterThan', operand1: sdf.format(timehourago)],
						[propertyName: 'outcome', operator: 'equals', operand1: 'error']]
                        
result = ef.findObjects(
                objectType: 'job',
                filters: arg).objectId

println "Found " + result.size() + " results."
result.each {
	def user = ""
	def result = ef.getJobDetails(
                jobId: it).job
	if (result.launchedByUser != null) { user = result.launchedByUser } else { user = result.liveProcedure }
    println result.jobName + " in project " + result.projectName + " started by " + user + " has status " + result.outcome
}

