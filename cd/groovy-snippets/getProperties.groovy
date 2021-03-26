import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()

def props = ef.getProperties(propertyName: "/myProject/dynamicoptions").propertySheet.property.each { prop ->
    println prop.propertyName
}