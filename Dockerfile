# This Dockerfile creates an EAP 7.0 server and cahnges files in order to support DB2

FROM docker.optum.com/jboss-eap-7/eap70-openshift:latest

COPY target/*.war /opt/eap/standalone/deployments/AccumHub.war
