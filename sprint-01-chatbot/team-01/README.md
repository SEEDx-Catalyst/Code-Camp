# Code-Camp-Team1

# instructions on how to deploy your solution.

download code folder from https://github.com/SEEDx-Catalyst/Code-Camp/tree/master/sprint-01-chatbot/team-01
``
### Option 2: Deployment on Cloud Foundry, SAP Cloud Platform

1. To avoid the naming conflict, edit the application name in [manifest.yml](https://github.com/SEEDx-Catalyst/Code-Camp/blob/master/sprint-01-chatbot/team-01/presales-assistance-bot/presales_assistance_webhook.js). For example, "presales-assistance"
2. Run the command to login to Cloud Foundry, SAP Cloud Platform,and deploy the app.
    ```
    cf login
    ...
    cf push
    ```
    Once the app is started, as a result, you now obtain the web hook url for your bot. for exmaple: https://presales-assistance.cfapps.us10.hana.ondemand.com/
## Apply the webhook url to the skill of your bot
Open your bot on SAP Conversation AI
* Go to tab Build and create a skill for the target intent 
* Setup the trigger as when the target intent is present. 
* For action, use Call Webhook to reply, and fill in the webhook url as step of deployment.

![Output](https://github.com/SEEDx-Catalyst/Code-Camp/blob/master/sprint-01-chatbot/team-01/presales-assistance-bot/slack_presales-assistance-bot%20v2.1_1.gif)


![Output](https://github.com/SEEDx-Catalyst/Code-Camp/blob/master/sprint-01-chatbot/team-01/presales-assistance-bot/presales-assistance-bot%20v1.gif)


![SAP Conversational AI](https://i.imgur.com/mKxXnBu.png)

# SEEDx Chatbot Team1

## Purpose
To develop a chatbot using SAP Conversational AI.

## Chatbot Link: 
 Visit Chatbot (https://cai.tools.sap/sanjeewa/presales-assistance)

## Team Formation Required: 
Middle-tier / Backend Developer, Chatbot Developer

Team 1		

Dinesh Gudapalli	dinesh.gudapalli@vestrics.in (Roll : )

Sanjeewa	sanjeewa@pristineworldwide.com (Roll :Chatbot Developer & Roll :Middle-tier / Backend Developer)

Manimekalai S manimekalais@gain-insights.com (Roll : )


## Main Tasks: 
Your chatbot must know how to answer, “What is the top 5 opportunity in my business”.

## License
The source code is under MIT license. Please kindly check the LICENSE. Here is to highlight that THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED. Therefore no support available.


