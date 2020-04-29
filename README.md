# Sponsorship
- Sends emails to employers that can only be applied through email
- Extracts company information from UK gov Tier 2 sponsorship list and intersects it with company data
- Allows you to keep track of applications through checklist API

Required properties files
- secrets.properties
- email.properties

### Api secrets
Required properties in **secrets.properties**<br/>
gmail=<your gmail address><br/>
gmail=<your gmail password - could be app password><br/>
api_key=<Company House API key><br/>

### Email properties
Required properties for email.properties:<br/>
email.service.attachmentname=<attachment name to be shown in the email e.g. John Doe CV.pdf><br/>
email.service.cvpath=<path to cv><br/>
email.service.emailtemplate=<path to email template as .txt file, must be in the resources folder><br/>

.txt file could contain placeholder {jobName}</br>
e.g. Please find attached copy of my CV for consideration for the {jobName}.