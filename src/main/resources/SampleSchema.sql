SET SCHEMA App;

CREATE TABLE App.SampleTable
(
	Id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Description LONG VARCHAR,
	Body LONG VARCHAR
);

SELECT * FROM SYS.SYSSCHEMAS;


INSERT INTO App.SampleTable (Description, Body) VALUES('2', 'Body2');
INSERT INTO App.SampleTable (Description, Body) VALUES('3', 'Body3');
INSERT INTO App.SampleTable (Description, Body) VALUES('4', 'Body4');
INSERT INTO App.SampleTable (Description, Body) VALUES('5', 'Body5');
INSERT INTO App.SampleTable (Description, Body) VALUES('6', 'Body6');


SELECT * FROM SampleTable;