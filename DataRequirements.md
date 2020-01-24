# Data Requirements

##### The system must manage the following data and constrains: 

1. Departments: Each department has one or more faculty and one of them is selected to be the chair of the department.Each faculty can only be the chair of a department.  

1. Searches. A **search** is defined as the process of hiring a faculty for an announced position by a department, and **everything associated** with it. Each search contains name, start date, end date, estimated salary (per hour), and position's description. 

1. Search committees. A search committee is formed for each search. A search committee consists of a number of faculty from a department and one of them is designated as the committee chair. A faculty can belong to one or more committees as well as can be the chair of different committees.

1. Various documents like Position Announcement, Recruitment Questionnaire, training certificates of the search committee members, reports, and so on.

  	* Some documents must be approved by one or more administrators in order for the search to proceed. For example, the Position Announcement must be approved by the college dean and then by the Associate Vice President of Faculty Affairs. To avoid creating and maintaining a complex organization hierarchy in the system, we'll assume that certain users in the system are designated as administrators, and they can access and approve any document.
	 	 
  	* Some documents may go through multiple revisions. A  new revision contains a new version of the document (usually in the form of a MS Word or a PDF file), and optionally, some notes or comments about the new version.
 
 	* For revision operations, the faculty first create a document, then create a new version every time they upload a new file to the document. For every version, the faculty can add notes or comments related to the new created version.</li>

 	* When administrators approve a document, they will approve the latest version of each document. Each administrator can approve multiple documents.

1. A log that records the recruitment and outreach activities by the faculty. In other words, for each search, the faculty can create several logs to record search's activities.</li>

1. Applications, which include the information and files submitted by applicants (a.k.a. candidates) of the position (a.k.a. search). Each application will have one or more references which can be used for reference checks during hiring process.</li> 

1. Evaluations of the candidates by the search committee. Each evaluation should belong to each application and be evaluated by one committee. Note that there are several rounds of evaluations: application screening and review, telephone/Skype interviews and on-campus interviews. All evaluation rounds' information is stored in the evaluation of each applicant. 

1. Reports of reference checks. Those reports should include the faculty, who write the report, information and references' information.
 
1. Various email templates. The system will communicate with the candidates by email, e.g. informing a candidate that he or she has passed the initial review and advanced to Skype interview. There's lots of boilerplate text in those emails, and such text should be kept in the system to generate emails. In shorts, the system should store the email templates which could be considered as text in simple case.
