cls
@echo off
echo Repertoire de travail 
cd
echo ---------------------------------------------------

echo Purge du script
del generatedScript.sql
set path=%path%;C:\Program Files\MySQL\MySQL Server 5.1\bin

echo Generation du script
echo select 'Desactivation des contraintes de foreign keys' as '-------------------------------';>generatedScript.sql
echo SET FOREIGN_KEY_CHECKS = 0;>>generatedScript.sql
echo select 'Import de la structure des tables' as '-------------------------------';>>generatedScript.sql
type DomainModel.SQL >>generatedScript.sql
echo select 'Import des structures des tables termine' as '-------------------------------';>>generatedScript.sql
echo select 'Activation des contraintes de foreign keys' as '-------------------------------';>>generatedScript.sql
echo SET FOREIGN_KEY_CHECKS = 1;>>generatedScript.sql
echo select 'import des donnees en table' as '-------------------------------';>>generatedScript.sql
cd datas
for %%a IN (*.sql) do (
echo select 'traitement du fichier %%a' as '-------------------------------';>>../generatedScript.sql
type "%%a">>../generatedScript.sql
)
cd ..
echo select 'fin des traitements' as '-------------------------------';>>generatedScript.sql
echo Script genere, veuillez verifier son contenu avant execution (return pour executer)
pause
mysql -h localhost -u root -D quizz < generatedScript.SQL 2>&1
pause
exit

