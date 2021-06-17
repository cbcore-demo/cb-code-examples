import com.electriccloud.client.groovy.ElectricFlow
def allalive = "No"
def resources = ""
def resourcelist= []

ElectricFlow ef = new ElectricFlow()

// Get resources from environment
// and see if any of them are still not alive

while (allalive != "Yes") {
    sleep 60000 // 60 seconds
    allalive = "Yes"
	resources = ef.getResourcesInEnvironmentTier(
        projectName: 'Custom018',
        environmentName: 'aws-cf-provisioned-env',
        environmentTierName: 'Tier 1')

    resources.resource.each { item ->
       	println item.resourceName + " has state " + item.agentState.alive
		if (item.agentState.alive != "1") {
            allalive = "No"
          	println "Pinging resource " + item.resourceName
           	ef.pingResource(resourceName: item.resourceName)
        }
    }
}

println "All resources in the environment are now alive"