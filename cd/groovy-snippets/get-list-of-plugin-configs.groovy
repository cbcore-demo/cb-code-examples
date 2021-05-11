import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()

allconfigs = ef.getProperties(propertyName: "/plugins/EC-Jenkins/project/Jenkins_cfgs")
confignames = allconfigs.propertySheet.property.propertyName
println confignames

confignames.each {
  println it
}