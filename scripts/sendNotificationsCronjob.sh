#!/bin/bash
# This script should be scheduled as a Kubernetes cronjob to run every minute
# to trigger notification sending by calling the Notification System endpoint
curl --location --request POST 'http://localhost:8080/triggerNotificationSending'
