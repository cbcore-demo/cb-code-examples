// Get instances from DescribeAutoScalingGroup
import com.electriccloud.client.groovy.ElectricFlow
import groovy.xml.*

ElectricFlow ef = new ElectricFlow()

// Identify instances
def DescribeAutoScalingGroupsResponse = new XmlSlurper().parseText('''$[/myStageRuntime/tasks/Get ASG Instance Details/job/outputParameters/result]''')
List instances = DescribeAutoScalingGroupsResponse.DescribeAutoScalingGroupsResult.AutoScalingGroups.member.Instances.'**'.findAll{ node -> node.name() == 'InstanceId' }*.text()
println "Instances: " + instances

instances.each {
    def thisitem = it
    	println "Setting property /myStageRuntime/instances/" + thisitem + " with value tbd"
	    ef.setProperty propertyName: '/myStageRuntime/instances/' + thisitem, value: 'tbd'
}