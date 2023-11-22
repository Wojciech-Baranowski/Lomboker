# Lomboker

**Description:**

A web application for removing boilerplate from java code based on AST structure. 
It allows to clean any .java file content from redundant accessors and mutators by substituting them with lombok annotations. 
Works by pasting the code into textarea, selecting desired options and copying cleaned code from second textarea.

**Setup**

Run docker-compose up --build in the same directory where docker-compose.yaml is located.

**Ports:**

- Nginx on 3721
