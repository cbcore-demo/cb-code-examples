resource "kubernetes_namespace" "cloudbees-core" {
  metadata {
    name = "cloudbees-core"
  }
}

data "helm_repository" "cloudbees" {
  name = "cloudbees"
  url = "https://charts.cloudbees.com/public/cloudbees"
}

resource "helm_release" "cloudbees-core" {
  name       = "cloudbees-core"
  repository = "data.helm_repository.cloudbees.metadata.0.name"
  chart      = "cloudbees/cloudbees-core"
  namespace = "cloudbees-core"

  set {
    name = "name"
    value = "cloudbees-core"
  }

  set {
    name = "OperationsCenter.ServiceType"
    value = "ClusterIP"
  }
}