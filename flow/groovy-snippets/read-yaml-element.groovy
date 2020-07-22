// Sample yaml file for below code
//
// Product:
//   - Name: myproduct-name
//     Owner: can be anything
//     Description: Creates an ECS Cluster with AutoScaling
//     Distributor: normally the name as owner
//     SupportDescription: Available on chat - no SLA commitments, best endeavours
//     SupportEmail: something@domain.com
//     SupportUrl: http://somesubdomain/
//     Tags:
//     - Key: product-type
//       Value: ecs
//   - Name: myproduct-name2
//     Owner: can be anything
//     Description: Creates an ECS Cluster with AutoScaling
//     Distributor: normally the name as owner myproduct-name2
//     SupportDescription: Available on chat - no SLA commitments, best endeavours
//     SupportEmail: something@domain.com
//     SupportUrl: http://somesubdomain/
//     Tags:
//     - Key: product-type
//       Value: ecs



import org.yaml.snakeyaml.Yaml

Yaml parser = new Yaml()
def example = parser.load(("/tmp/metadata.yaml" as File).text)

//List results = example.Product.Name

//println example.Product.Name
//results.each {
//    println it + "\n"
//}

def result = example.Product.findAll { it.Name == 'myproduct-name2' }.Distributor

println result