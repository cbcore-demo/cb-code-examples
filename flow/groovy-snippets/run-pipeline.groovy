import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*

ElectricFlow ef = new ElectricFlow()

def result = ef.runPipeline(
                projectName: '$[/myProject/name]',
                releaseName: 'Custom006 Test Release',
                pipelineName: 'Custom006 Top Level Pipeline_17'
)