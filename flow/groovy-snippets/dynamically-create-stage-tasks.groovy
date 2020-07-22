import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.apis.model.*
import com.electriccloud.client.groovy.models.ActualParameter

ElectricFlow ef = new ElectricFlow()

def params = [
        new ActualParameter(actualParameterName: 'Param1', value: 'test1'),
        new ActualParameter(actualParameterName: 'Param2', value: 'test2')]

def result = ef.createTask(
                projectName: '$[/myProject/name]',
                releaseName: '$[/myRelease/name]',
                pipelineName: '$[/myPipeline/name]',
                taskName: 'Task1',
                stageName: 'Dynamic',
                taskType: 'PIPELINE',
                subpipeline: 'Custom006 Called pipeline',
                subproject: 'Custom006',
                actualParameters: params
)