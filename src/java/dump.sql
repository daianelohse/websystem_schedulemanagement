
INSERT INTO `tipo_ambiente` VALUES (1,'SALA','SALA','SALA'),(2,'Laboratório de Informática com VDI','Laboratório de Informática com VDI','LABINFVDI');
INSERT INTO `ambiente` VALUES (5,0,'A',30,NULL,6,'A6',0,1,2),(6,2,'A',40,NULL,20,'A20',0,1,2),(7,0,'A',30,NULL,3,NULL,0,1,1),(8,0,'A',35,NULL,4,NULL,0,1,1),(9,0,'H',1,NULL,22,NULL,0,NULL,NULL),(10,0,'H',11,NULL,22,NULL,0,2,2);
INSERT INTO `localizacao` VALUES (1,'Senai'),(2,'Giassi');
INSERT INTO `modalidade` VALUES (1,NULL,'Aprendizagem Industrial','AI'),(2,NULL,'Curso Técnico','T'),(3,NULL,'Superior de Tecnologia','ST'),(4,NULL,'Qualificação Profissional','QA'),(5,NULL,'Pós-Graduação','PG');
INSERT INTO `curso` VALUES (1,NULL,'Técnico em Informática','TINF',2),(2,NULL,'Técnico em Produto de Moda','TPM',2),(3,NULL,'Aprendizagem Industrial em Informática','AIINF',1),(4,NULL,'Aprendizagem Industrial em Confeccionador de Moldes e Roupas','AICMR',1);

INSERT INTO `turma` VALUES (2,2015,1,1,1,2),(3,2015,2,1,1,2);

INSERT INTO `curso` VALUES (1,NULL,'Técnico em Informática','TINF',2),(2,NULL,'Técnico em Produto de Moda','TPM',2),(3,NULL,'Aprendizagem Industrial em Informática','AIINF',1),(4,NULL,'Aprendizagem Industrial em Confeccionador de Moldes e Roupas','AICMR',1);

INSERT INTO `pessoa` VALUES (1,'1997-04-08','nirte','ni','no','no'),(2,'1997-04-08','nirgeonic@gmail.com','Nicolas José Cordeiro Viana','(47) 9129-2646',NULL);

INSERT INTO `colaborador` VALUES (1,1,0,'a',20020,'a',8,'80920',1,1,1),(2,1,NULL,'nicolas.viana@sc.senai.br',2332,NULL,8,NULL,0,NULL,2);

INSERT INTO `login` VALUES (1,'123','nicolas.viana',2);

INSERT INTO `turma` VALUES (2,2015,1,1,1,2),(3,2015,2,1,1,2),(4,2016,1,1,1,3),(5,2016,1,1,1,1),(6,2016,1,1,1,4),(7,2016,1,1,0,3),(8,2016,1,1,1,2),(9,2016,2,1,1,4);
INSERT INTO `dia_trabalho` VALUES (1,1,1,5,2),(2,3,1,2,2),(3,1,1,2,2),(4,1,1,4,2),(5,0,1,0,2),(6,3,1,3,2),(7,4,1,5,2),(8,2,1,0,2),(9,3,1,5,2),(10,5,1,5,2),(11,3,1,0,2),(12,2,1,5,2),(13,5,1,4,2),(14,4,1,1,2),(15,0,1,2,2),(16,3,1,1,2),(17,2,1,4,2),(18,2,1,1,2),(19,4,1,4,2),(20,1,1,1,2),(21,1,1,0,2),(22,5,1,2,2),(23,0,1,5,2),(24,4,1,0,2),(25,3,1,4,2),(26,5,1,1,2),(27,1,1,3,2),(28,2,1,3,2),(29,5,1,0,2),(30,4,1,3,2),(31,4,1,2,2),(32,0,1,1,2),(33,0,1,3,2),(34,0,1,4,2),(35,2,1,2,2),(36,5,1,3,2);
INSERT INTO `unidade_curricular` VALUES (1,30,NULL,'Comunicação Oral e Escrita','COE',1),(2,180,'','Programação WEB','PWEB',2),(3,80,'Somente laboratório','Testes de Software','TSOF',2),(4,60,'50% sala e 50% laboratório de informática','Comunicação e Oral Escrita','COE',2),(5,30,'','Gestão de Pessoas','GESP',2),(6,70,'','Arquitetura de Sistemas','ARQS',1);
INSERT INTO `permissao` VALUES (3,'ROLE_ADMIN',1),(4,'ROLE_PROFESSOR',1),(5,'ROLE_COORDENADOR_MODALIDADE',1),(6,'ROLE_RELATS',1),(7,'ROLE_COORDENADOR_CURSO',1),(8,'ROLE_FUNCIONARIO',1);
