import jenkins.model.*

def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()


def plugin = uc.getPlugin("jackson2-api", "2.10.2")
println("Installing jackson2-api")
plugin.deploy()
def plugin2 = uc.getPlugin("build-failure-analyzer", "1.25.1")
println("Installing build-failure-analyzer")
plugin2.deploy()

instance.save()
if (installed)
instance.doSafeRestart()