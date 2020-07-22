import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*

ElectricFlow ef = new ElectricFlow()

def result = ef.startRelease(
                projectName: '$[/myProject/name]',
                releaseName: 'Custom006 Test Release'
)