insert into project(project_name, project_description) values ('Contact Manager',null);
insert into project(project_name, project_description) values ('Bug Tracking',null);

insert into bug (project_id, reporter, title, created_date, priority, status, assignee_id) values (1, 'Bruce', 'Mixed case URLs cause problem when hosted on UNIX', '2014-03-19', 1, 'New', 'Bruce');
insert into bug (project_id, reporter, title, created_date, priority, status, assignee_id) values (1, 'Jenna', 'Add option to put count on every fram', '2014-04-01', 2, 'Investigated', 'Shay');
insert into bug (project_id, reporter, title, created_date, priority, status, assignee_id) values (2, 'Shay', 'Auditing webpages with counter', '2014-03-29', 3, 'Closed', 'Tom');
insert into bug (project_id, reporter, title, created_date, priority, status, assignee_id) values (2, 'Matthew', 'Missing cases', '2014-03-21', 4, 'Assigned', 'Jay');
insert into bug (project_id, reporter, title, created_date, priority, status, assignee_id) values (1, 'Tommy', 'All counts are wiped when maxpages is exceeded', '2014-03-23', 5, 'Resolved', 'Dave');

insert into bug_assignee (bug_id, assignee_id, position_title) values (1, 'Bruce', 'Developer');
insert into bug_assignee (bug_id, assignee_id, position_title) values (2, 'Shay', 'Leader');
insert into bug_assignee (bug_id, assignee_id, position_title) values (3, 'Tom', 'Developer');
insert into bug_assignee (bug_id, assignee_id, position_title) values (4, 'Jay', 'Tester');
insert into bug_assignee (bug_id, assignee_id, position_title) values (5, 'Dave', 'Intern');
