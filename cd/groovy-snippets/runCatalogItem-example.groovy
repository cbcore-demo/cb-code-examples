import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*

ElectricFlow ef = new ElectricFlow()

// This Service Catalog Item example used Formal Parameters defined
// on the Service Catalog Item instead of a form
def params = [
    new ActualParameter(actualParameterName: 'config', value: 'GitHub CloudBees Guru'),
    new ActualParameter(actualParameterName: 'gitRepo', value: 'https://github.com/cloudbees-guru/cd-examples-library.git'),
    new ActualParameter(actualParameterName: 'gitBranch', value: 'main'),
    new ActualParameter(actualParameterName: 'rsrcName', value: 'local'),
    new ActualParameter(actualParameterName: 'dest', value: '/tmp/import-dsl-from-git'),
    new ActualParameter(actualParameterName: 'relPath', value: 'cd-project-export'),
    new ActualParameter(actualParameterName: 'overwrite', value: 'true'),
    new ActualParameter(actualParameterName: 'cleanup', value: 'true')]

def result = ef.runCatalogItem(
                projectName: 'Electric Cloud',
                catalogName: 'DSL',
                catalogItemName: 'Import DSL from Git',
                actualParameters: params)