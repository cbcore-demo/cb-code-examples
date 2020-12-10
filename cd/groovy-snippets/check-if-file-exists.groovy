import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*

ElectricFlow ef = new ElectricFlow()

def fileName = "/tmp/aws-sc-productlist"
def testFile = new File(fileName)
if (testFile.exists()) {
    ef.setProperty(propertyName: "/myStageRuntime/fileexists", value: "TRUE")
} else {
    ef.setProperty(propertyName: "/myStageRuntime/fileexists", value: "FALSE")
}