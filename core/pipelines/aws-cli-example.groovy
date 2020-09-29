// This pipeline uses the CloudBees AWS CLI plugin
def res

pipeline {
    parameters {
        string(name: 'Version', defaultValue: '1', description: 'Product Artifact Name', trim:true)
        string(name: 'PortfolioId', defaultValue: '', description: 'Id of the portfolio the product should be associated with', trim:true)
        string(name: 'CommitHash', defaultValue: '', description: 'Hash of the commit to be built', trim:true)
    }
    agent { label 'aws-cli' }
    stages {
        stage('Prep') { 
            steps {
                echo "Version parameter is set to [$Version]\n"
                echo "PortfolioId is set to [$PortfolioId]\n"
            }
        }
        stage('Create Product') { 
            steps {
                withAwsCli( defaultRegion: 'eu-west-2') { 

                    script {

                        res = sh (
                            script: '''aws servicecatalog create-product --name "Linux Desktop" --owner "Phil Cherry (pcherry@cloudbees.com)" --product-type CLOUD_FORMATION_TEMPLATE --provisioning-artifact-parameters Name=$Version,Info={LoadTemplateFromURL=https://pcherry-s3-bucket.s3.amazonaws.com/AWS-Service-Catalog/aws-sc-linuxdesktop-template},Type=CLOUD_FORMATION_TEMPLATE --query "ProductViewDetail.ProductViewSummary.ProductId"''',
                            returnStdout: true
                        ).trim()
                        echo "Created ProductId is ${res}"
                    }
                }
            }
        }
        stage('Add Product to Portfolio') {
            steps {
                withAwsCli( defaultRegion: 'eu-west-2') { 

                    script {

                        sh (
                            script: '''aws servicecatalog associate-product-with-portfolio  --product-id ''' + res + ''' --portfolio-id $PortfolioId''',
                            returnStdout: true
                        ).trim()
                    }
                }
            }
        }
    }
}