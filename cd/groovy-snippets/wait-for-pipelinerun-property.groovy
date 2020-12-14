import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()
def Iteration = 1
def RTStatus = ""

def GetRTStatus = {
    RTStatus=ef.getProperty(
        propertyName: '/projects/$[RTProject]/flowRuntimes/$[WaitForFlowRuntime]/$[controlFlag]').property.value

    println "Step outcome is: " + RTStatus
    return RTStatus
}

while (GetRTStatus() != 'True' && Iteration < 1000) {
    sleep 5000
    Iteration=Iteration+1
    println 'Check #' + Iteration
}

if (RTStatus != 'True') {
    println "Release Train has not been released in a reasonable time."
    System.ext(-1)
}

println "Release Train is allowing this pipeline to continue."