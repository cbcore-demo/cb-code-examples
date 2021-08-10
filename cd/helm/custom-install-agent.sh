#!/bin/bash -x
PATH=$PATH:/opt/cbflow/bin
installPackages() {
  apt-get update -y
  apt-get install wget -y
  apt-get install curl -y
  apt-get install lsb-core
  apt-get install unzip -y
}
installKubectl() {
  apt-get install -y apt-transport-https ca-certificates curl
  curl -fsSLo /usr/share/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg
  echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | tee /etc/apt/sources.list.d/kubernetes.list
  apt-get update -y
  apt-get install -y kubectl
}
installHelm() {
  curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3
  chmod 700 get_helm.sh
  ./get_helm.sh
}
installPackages >>/tmp/package-install.log
installKubectl >>/tmp/install-kubectl.log
installHelm >>/tmp/install-helm.log