import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()

pipelinedetails = ef.getPipelineRuntimeDetails([flowRuntimeIds:["$[pipelineRuntimeId]"]]).flowRuntime[0].stages.stage

pipelinedetails.each {
	String thisstage = it.name
    stagetasks = ef.getPipelineStageRuntimeTasks(flowRuntimeId:"$[pipelineRuntimeId]", stageName: thisstage).task
	stagetasks.each { if (it.status == 'error') { println "Stage " + thisstage + ", task " + it.taskName + " failed, need to notify on it" } }
}
