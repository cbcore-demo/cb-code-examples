import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()

try {
    def result = ef.attachPipelineRun(
                    projectName: '$[/myProject/name]',
                    releaseName: '$[RTName]',
                    flowRuntimeId: '$[/myPipelineRuntime/id]')
} catch (all) {
    assert true
}