# Reach_Automation
Reach_Automation_Code (Maven Project): This repository is to build on Java with Maven.

1. Language used : Java
2. Tool used : Selenium
3. Browser used : Chrome
4. Chrome version: 103.0.5060.114 (Chrome webdriver 103 is used for automation)
5. Tool : Eclipse IDE

This code contain:
1. Driver class 
2. CompanyPortalWebAppTest class
3. Support_Functions class

Execute CompanyPortalWebAppTest:
CompanyPortalWebAppTest class: Is a JUnit class and execution should start from this file(Can be executable in JUnit Test Run Mode).

Outputfiles:
1. Screenshot Folder: logo's are saved in Screenshot folder under Reach Project.
2. OutputJson Folder : Contain "data.jason" file where Company details are saved after execution.This file is deleted before every execution and new file is created with current execution data at end of execution.

Contact information Fetched:
1. Address
2. Fax
3. Medical Information e-mail
4. Medical Information Direct Line
5. Telephone
 This code fetch the above contact details and  display them as Jason Array.
 
 
 Note:
 There are some pages where only 3 companies exist. In this case, code captures only first and last company details(As 3rd and last company are same).
 


