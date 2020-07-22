import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.apis.model.*

String fileContents = new File('/tmp/aws-sc-productlist').text.trim()

println "fileContents"+fileContents

ElectricFlow ef = new ElectricFlow()

def result = ef.setProperty(
                propertyName: '/myStageRuntime/productlist',
                value: fileContents)