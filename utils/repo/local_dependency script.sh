mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=requestProcedures -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/requestProcedures.jar
mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=onem2mxsd -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/onem2mxsd-3.9.jar
mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=originatorActions -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/originatorActions.jar
mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=httpBindingClient -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/httpBindingClient.jar
mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=common -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/common.jar
mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=mqttBinding -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/mqttBinding.jar
mvn deploy:deploy-file -DgroupId=cdot.onem2m -DartifactId=mqtt -Dversion=3.9 -Durl=file:./ -DrepositoryId=repo -DupdateReleaseInfo=true -Dfile=../lib/mqtt.jar
